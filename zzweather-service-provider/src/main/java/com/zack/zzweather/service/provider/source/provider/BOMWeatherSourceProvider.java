package com.zack.zzweather.service.provider.source.provider;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import com.zack.zzweather.service.provider.source.AbstractProvider;
import org.springframework.stereotype.Service;

/**
 *  Source data of Australia weather, provided by BOM of au gov.
 *  Just not implement now.
 */
@Service
public class BOMWeatherSourceProvider extends AbstractProvider {


    public BOMWeatherSourceProvider() {
        /**
         *  Setup the source name of this provider.
         */
        super("BOM");
    }

    @Override
    public WeatherDTO getCurrentWeatherByName(String name) {
        return null;
    }

    @Override
    public WeatherDTO getCurrentWeatherByGPS(String longitude, String latitude) {
        return null;
    }
}
