package com.kenzie.appserver.service;


import com.kenzie.appserver.config.ShipInformationCache;

import com.kenzie.appserver.repositories.ShipInformationRepository;
import com.kenzie.appserver.repositories.model.ShipInformationRecord;
import com.kenzie.appserver.service.model.ShipInformation;

public class ShipInformationService {
    ShipInformationRepository shipInformationRepository;
    ShipInformationCache cacheStore;





    // TODO Constructors
    public ShipInformationService(ShipInformationRepository shipInformationRepository, ShipInformationCache cacheStore) {
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
            shipInformationRecord.setEndTime(shipInformation.getEndTime());


            shipInformationRepository.save(shipInformationRecord);
            cacheStore.evict(shipInformation.getGameId());
        }
    }

    public ShipInformation getShipInformationById(String gameId) {
        ShipInformation cachedShipInformation = cacheStore.getShipInformation(gameId);

        // Check if ShipInformation is cached and return it if true
        if (cachedShipInformation != null) {
            return cachedShipInformation;
        }

        // if not cached, find the shipInformation
        ShipInformation shipInformationFromRepository = shipInformationRepository
                .findById(gameId)
                .map(shipInformation -> new ShipInformation(shipInformation.getGameId(),
                        shipInformation.getPlayerCoordinates(),
                        shipInformation.getAlienCoordinates(),
                        shipInformation.getPlayerHealth(),
                        shipInformation.getAlienHealth(),shipInformation.getRound()))
                .orElse(null);

        // if shipInformation found, cache it
        if (shipInformationFromRepository != null) {
            cacheStore.add(shipInformationFromRepository.getGameId(), shipInformationFromRepository);
        }

        // return shipInformation
        return shipInformationFromRepository;
    }

    public void deleteShipInformationById(String gameId) {



        shipInformationRepository.deleteById(gameId);
        cacheStore.evict(gameId);
    }
}
