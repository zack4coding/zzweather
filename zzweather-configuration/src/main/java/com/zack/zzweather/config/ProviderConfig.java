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
@ConfigurationProperties(prefix = "provider")
@Data
public class ProviderConfig {

    /**
     * This config setup the source name we connect
     * and get the weather data.
     */
    private String name;

    /**
     * This config setup the source key we connect
     * and get the weather data.
     */
    private String key;

    /**
     * This config setup the source url we connect
     * and get the weather data.
     */
    private String url;

    /**
     * This config setup the country we connect
     * and get the weather data.
     */
    private String country;


    /**
     * This config setup the postcode this system supported.
     */
    private List<String> postcodes;
}
