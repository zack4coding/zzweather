package com.zack.zzweather.service.provider.source.provider;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zack.zzweather.common.utils.HttpSender;
import com.zack.zzweather.config.ProviderConfig;
import com.zack.zzweather.service.api.dto.WeatherDTO;
import com.zack.zzweather.service.provider.source.AbstractProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherBitSourceProvider extends AbstractProvider {

    @Autowired
    private ProviderConfig providerConfig;

    private String PROVIDER_URL;
    private final long PROVIDER_UPDATE_CRON = 10L;

    private final String PROVIDER_POSTCODE_KEY = "postal_code";
    private final String PROVIDER_COUNTRY_KEY = "country";
    private final String PROVIDER_API_AUTH_KEY = "key";

    private String PROVIDER_API_AUTH_VALUE;
    private String PROVIDER_COUNTRY_VALUE;

    private Map<String, String> cityPostcodeMap = new HashMap<>();
    private Map<String, WeatherDTO> cache = new ConcurrentHashMap<>();

    public WeatherBitSourceProvider() {

        SOURCE_NAME = providerConfig.getName();

        PROVIDER_COUNTRY_VALUE = providerConfig.getCountry();
        PROVIDER_API_AUTH_VALUE = providerConfig.getKey();
        PROVIDER_URL = providerConfig.getUrl();

        for (String cityPostcode : providerConfig.getPostcodes()) {
            String[] cityPostcodeArray = cityPostcode.split("-");
            cityPostcodeMap.put(cityPostcodeArray[0],cityPostcodeArray[1]);
        }
    }

    @Override
    public WeatherDTO getCurrentWeatherByName(String name) {

        /**
         *  Get weather data from cache first, if not refresh by the city name.
         *  TODO Make Semaphores to mark whether have clients want know the current weather by flux.
         *  If yes, push the update data to client.
         */
        WeatherDTO weather = cache.get(name);
        if (weather == null) {
            return refreshWeather(name);
        }
        return weather;

    }

    @Override
    public WeatherDTO getCurrentWeatherByGPS(String longitude, String latitude) {
        return null;
    }

    private WeatherDTO getValueFromJson(JSONObject jsonObject, String name) {
        return new WeatherDTO(
                name,
                System.currentTimeMillis(),
                jsonObject.getJSONObject("weather").getString("description"),
                jsonObject.getString("temp")+"°C",
                jsonObject.getString("wind_spd")+"m/s"
        );
    }

    private WeatherDTO refreshWeather(String name) {
        try {

            Map params = new HashMap();
            params.put(PROVIDER_POSTCODE_KEY, cityPostcodeMap.get(name));
            params.put(PROVIDER_COUNTRY_KEY, PROVIDER_COUNTRY_VALUE);
            params.put(PROVIDER_API_AUTH_KEY, PROVIDER_API_AUTH_VALUE);
            String response = HttpSender.getInstance().doGet(
                    PROVIDER_URL,
                    params
            );
            //JSON file to Java object
            JSONObject jsonObject = JSON.parseObject(response);

            return getValueFromJson(jsonObject, name);
        } catch (Throwable t) {
            throw new RuntimeException("Refresh weather data from source got a Exception.");
        }
    }

    /**
     *  This func should be impl in module scheduler.
     *  Due to delivery deadline, write as this.
     */
    @Scheduled(fixedDelay = PROVIDER_UPDATE_CRON)
    public void taskUpdate() {
        if (cache.size() > 0) {
            for (WeatherDTO weatherDTO : cache.values()) {
                WeatherDTO updated = refreshWeather(weatherDTO.getCity());
                cache.put(weatherDTO.getCity(), updated);
            }
        }
    }
}
