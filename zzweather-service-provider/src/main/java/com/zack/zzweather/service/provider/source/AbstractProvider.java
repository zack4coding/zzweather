package com.zack.zzweather.service.provider.source;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractProvider implements Source {

    /**
     *  The source name which used as key to identify a provider.
     */
    protected String SOURCE_NAME;

    public AbstractProvider(String SOURCE_NAME) {
        this.SOURCE_NAME = SOURCE_NAME;
    }

    public AbstractProvider() {
    }

    @Autowired
    private SourceManager manager;

    /**
     * All source provider need to register themselves to manager,
     * So abstract the register func to this class.
     */
    @PostConstruct
    public void registerThisToManager() {
        manager.register(SOURCE_NAME, this);
    }

}
