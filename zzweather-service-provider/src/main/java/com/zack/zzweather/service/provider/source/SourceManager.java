package com.zack.zzweather.service.provider.source;

import com.zack.zzweather.config.ZZWeatherConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Source maintained by using
 *  Strategy Pattern.
 */
@Service
@Slf4j
public class SourceManager {

    /**
     *  Hold the source map which be identify by provider name as key.
     */
    private Map<String, Source> sourceHolderMap = new HashMap<>();

    /**
     *  If you'd like to supply source data to this system,
     *  please register yourself in with this method.
     * @param provider
     * @param source
     */
    public void register(String provider, Source source) {
        sourceHolderMap.put(provider, source);
    }

    @Autowired
    private ZZWeatherConfig config;

    /**
     *   Get the activity source which setup by config.
     *   By the way there is another elegant way to conduct this case,
     *    by using spring annotation @Conditional
     * @see org.springframework.context.annotation.Conditional
     * @return source
     */
    public Source getActivitySource() {
        String configProvider = config.getSource();
        if (configProvider == null || configProvider.equals("")) {
            log.error("Please setup which source do you want to provide the weather data");
            // TODO: Throw no source setup Exception.
        }
        return sourceHolderMap.get(configProvider);
    }

}
