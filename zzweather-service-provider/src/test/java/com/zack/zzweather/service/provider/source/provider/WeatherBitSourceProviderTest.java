package com.zack.zzweather.service.provider.source.provider;

import com.zack.zzweather.config.ProviderConfig;
import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WeatherBitSourceProviderTest {

    WeatherBitSourceProvider provider;

    @Before
    public void init() {
        ProviderConfig config = new ProviderConfig();
        config.setName("WeatherBit");
        config.setCountry("AU");
        config.setPostcodes(Arrays.asList("Sydney-2000"));
        config.setUrl("https://api.weatherbit.io/v2.0/current");
        config.setKey("212a258a95b84252bedc97d973b97bbf");

        provider = new WeatherBitSourceProvider(config);

    }

    @Test
    public void getCurrentWeatherByName() {
        WeatherDTO weatherDTO = provider.getCurrentWeatherByName("Sydney");
        assertNotNull(weatherDTO);
        assertNotNull(weatherDTO.getCity());
        assertNotNull(weatherDTO.getTemperature());
        assertNotNull(weatherDTO.getWeather());
        assertNotNull(weatherDTO.getWind());
        assertTrue(weatherDTO.getCity().equals("Sydney"));
    }
}