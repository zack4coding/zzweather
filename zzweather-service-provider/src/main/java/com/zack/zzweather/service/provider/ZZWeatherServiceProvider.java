package com.zack.zzweather.service.provider;

import com.zack.zzweather.service.api.ZZWeatherServiceApi;
import com.zack.zzweather.service.api.dto.WeatherDTO;
import com.zack.zzweather.service.provider.source.SourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ZZWeatherServiceProvider implements ZZWeatherServiceApi {

    /**
     *  Update rate per 10min.
     */
    private final long PROVIDER_UPDATE_CRON = 1000 * 60 * 10L;

    private Map<String, WeatherDTO> cache = new ConcurrentHashMap<>();

    @Autowired
    private SourceManager sourceManager;

    @Override
    public WeatherDTO getCurrentWeather(String city) {

        /**
         *  Get weather data from cache first, if not refresh by the city name.
         *  TODO Make Semaphores to mark whether have clients want know the current weather by flux.
         *  If yes, push the update data to client.
         */
        WeatherDTO weather = cache.get(city);
        if (weather == null) {
            weather = refreshWeather(city);
            cache.put(weather.getCity(), weather);
        }
        return weather;
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

    private WeatherDTO refreshWeather(String city) {
        return sourceManager.getActivitySource().getCurrentWeatherByName(city);
    }
}
