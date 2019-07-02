package com.zack.zzweather.service.provider.source.provider;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import com.zack.zzweather.service.provider.source.AbstractProvider;
import com.zack.zzweather.service.provider.source.Source;
import org.springframework.stereotype.Service;

/**
 *  Source data of Australia weather, provided by BOM of au gov.
 *  Just not implement now.
 */
@Service
public class BOMWeatherSourceProvider extends AbstractProvider implements Source {


    public BOMWeatherSourceProvider() {
        /**
         *  Setup the source name of this provider.
         */
        super("BOM");
    }

    @Override
    public WeatherDTO getCurrentWeatherByPostcode(String postcode) {
        return null;
    }

    @Override
    public WeatherDTO getCurrentWeatherByGPS(String longitude, String latitude) {
        return null;
    }
}
