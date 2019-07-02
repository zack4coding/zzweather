package com.zack.zzweather.service.provider.source;

import com.zack.zzweather.service.api.dto.WeatherDTO;

/**
 *  The abstracted func of get weather data form different source.
 */
public interface Source {

    /**
     * Get current weather data from source by name of city.
     * @param name
     * @return Weather data of the cities.
     */
    WeatherDTO getCurrentWeatherByName(String name);


    /**
     * Get current weather data from source by Postcode or Zcode of city.
     * @param longitude
     * @param latitude
     * @return Weather data of the GPS location.
     */
    WeatherDTO getCurrentWeatherByGPS(String longitude, String latitude);

}
