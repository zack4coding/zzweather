package com.zack.zzweather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This class maintained the system configuration properties.
 * These properties are static and loaded when the server startup.
 * We will make this module as a configuration service, if possible, like spring cloud config.
 */
@Configuration
@ConfigurationProperties(prefix = "zzweather")
@Data
public class ZZWeatherConfig {

    /**
     * This config setup which source provider we connect
     * and get the weather data.
     */
    private String source;

    /**
     * The city list that we support to get weather information.
     * So this system can add more supported city by change configuration file.
     */
    private List<String> locations;
}
