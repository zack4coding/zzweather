package com.zack.zzweather.service.provider.source;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SourceTest {

    private Source source;

    @Before
    public void createSource() {

    }

    @Test
    public void testGetCurrentWeatherByPostcode() {
        WeatherDTO weatherDTO = source.getCurrentWeatherByPostcode("AU300000");
        assertNotNull(weatherDTO);
        assertNotNull(weatherDTO.getCity());
        assertTrue(weatherDTO.getCity().equals("Melbourne"));
    }
}