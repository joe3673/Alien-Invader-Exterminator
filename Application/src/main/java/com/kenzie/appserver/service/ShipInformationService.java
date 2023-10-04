package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.ShipInformationRepository;
import com.kenzie.appserver.repositories.model.LevelHistoryRecord;
import com.kenzie.appserver.repositories.model.ShipInformationRecord;
import com.kenzie.appserver.service.model.ShipInformation;

public class ShipInformationService {
    ShipInformationRepository shipInformationRepository;
    CacheStore cacheStore;


    // TODO Constructors
    public ShipInformationService(ShipInformationRepository shipInformationRepository, CacheStore cacheStore) {
        this.shipInformationRepository = shipInformationRepository;
        this.cacheStore = cacheStore;
    }


    // TODO Methods
    public ShipInformation addShipInformation(ShipInformation shipInformation) {
        ShipInformationRecord shipInformationRecord = new ShipInformationRecord();

        shipInformationRecord.setGameId(shipInformation.getGameId());
        shipInformationRecord.setPlayerCoordinates(shipInformation.getPlayerCoordinates());
        shipInformationRecord.setAlienCoordinates(shipInformation.getAlienCoordinates());
        shipInformationRecord.setPlayerHealth(shipInformation.getPlayerHealth());
        shipInformationRecord.setAlienHealth(shipInformation.getAlienHealth());
        shipInformationRecord.setRound(1);
        shipInformationRecord.setStartTime(System.currentTimeMillis());

        shipInformationRepository.save(shipInformationRecord);

        return shipInformation;
    }

    public void updateShipInformation(ShipInformation shipInformation) {
        if (shipInformationRepository.existsById(shipInformation.getGameId())) {
            ShipInformationRecord shipInformationRecord = new ShipInformationRecord();
            shipInformationRecord.setGameId(shipInformation.getGameId());
            shipInformationRecord.setPlayerCoordinates(shipInformation.getPlayerCoordinates());
            shipInformationRecord.setAlienCoordinates(shipInformation.getAlienCoordinates());
            shipInformationRecord.setPlayerHealth(shipInformation.getPlayerHealth());
            shipInformationRecord.setAlienHealth(shipInformation.getAlienHealth());

            shipInformationRepository.save(shipInformationRecord);
            cacheStore.evict(shipInformation.getGameId());
        }
    }

    public void deleteShipInformationById(String gameId) {

        shipInformationRepository.deleteById(gameId);
        cacheStore.evict(gameId);
    }
}
