package com.zack.zzweather.controller;

import com.zack.zzweather.service.api.ZZWeatherServiceApi;
import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class WeatherController {

    @Autowired
    private ZZWeatherServiceApi zzWeatherServiceApi;


    /**
     *  Push the weather data to client when request.
     *  Just impl flux to this Controller layer, maybe Customized Publisher and Subscribe to service layer
     *  in the further version.
     * @param city
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/current/event",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherDTO> current(String city) {
        return Flux.interval(Duration.ofSeconds(0),Duration.ofSeconds(3))
                .onBackpressureDrop()
                .map(l->zzWeatherServiceApi.getCurrentWeather(city))
                .log();
    }
}