package com.zack.zzweather.service.api;

import com.zack.zzweather.service.api.dto.WeatherDTO;

/**
 *  ZZWeather Service offered interface.
 */
public interface ZZWeatherServiceApi {


    /**
     *  Get weather info by city name.
     * @param city name.
     * @return
     */
    WeatherDTO getCurrentWeather(String city);

}
