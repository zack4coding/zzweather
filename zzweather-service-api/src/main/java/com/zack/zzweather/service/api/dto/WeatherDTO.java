package com.zack.zzweather.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

    private String city;
    private Long timestamp;
    private String weather;
    private String temperature;
    private String wind;

}
