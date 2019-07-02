package com.zack.zzweather.service.api;

import java.util.List;

public interface ZZLocationServiceApi {

    /**
     * Offer the supported locations of this service.
     * @return The name list of supported cities.
     */
    List<String> supportedLocations();
}
