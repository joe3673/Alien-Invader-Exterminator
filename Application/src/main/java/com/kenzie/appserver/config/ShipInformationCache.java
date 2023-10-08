package com.kenzie.appserver.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kenzie.appserver.service.ShipInformationService;
import com.kenzie.appserver.service.model.ShipInformation;

import java.util.concurrent.TimeUnit;

public class ShipInformationCache {
    private final LoadingCache<String, ShipInformation> shipInformation;


    // TODO Methods
    public ShipInformationCache(ShipInformationService service) {
        shipInformation = CacheBuilder.newBuilder()
                .expireAfterWrite(21, TimeUnit.SECONDS)
                .build(CacheLoader.from(service :: getShipInformationById));
    }

    public ShipInformation getShipInformation(String gameId) {
        return shipInformation.getUnchecked(gameId);
    }

    public void evict(String key) {
        shipInformation.invalidate(key);
    }

    public void add(String key, ShipInformation value) {
        shipInformation.put(key, value);
    }
}
