package com.zack.zzweather.controller;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
public class WeatherController {

    @GetMapping(path = "/current/event",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherDTO> current() {
        return Flux.interval(Duration.ofSeconds(0),Duration.ofSeconds(3))
//                .doFinally()
//                .doFirst()
                .onBackpressureDrop()
                .map(this::generateComment)
                .log()
                .flatMapIterable(x -> x);
    }

    private List<WeatherDTO> generateComment(long interval) {

        WeatherDTO obj = new WeatherDTO(
                "Melboume",
                System.currentTimeMillis(),
                "Mostly Cloudy",
                "9°C",
                "32km/h"
                );
        return Arrays.asList(obj);

    }
}