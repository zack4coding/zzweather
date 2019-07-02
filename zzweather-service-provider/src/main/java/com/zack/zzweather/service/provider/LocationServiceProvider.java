package com.zack.zzweather.service.provider;

import com.zack.zzweather.config.ZZWeatherConfig;
import com.zack.zzweather.service.api.ZZLocationServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceProvider implements ZZLocationServiceApi {

    @Autowired
    private ZZWeatherConfig config;

    @Override
    public List<String> supportedLocations() {
        // Get supported cities list from configuration module.
        List<String> locations = config.getLocations();
        if (locations != null && locations.size() > 0) {
            return locations;
        }
        // TODO: Throw no supported cities exception.
        return Arrays.asList();
    }
}
