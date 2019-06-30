package com.zack.zzweather.controller;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.List;

@RestController
public class BasicController {

    @GetMapping("/hello_world")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World");
    }


    @GetMapping(path = "/event",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<WeatherDTO> feed() {
        return Flux.interval(Duration.ofSeconds(0),Duration.ofSeconds(3))
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