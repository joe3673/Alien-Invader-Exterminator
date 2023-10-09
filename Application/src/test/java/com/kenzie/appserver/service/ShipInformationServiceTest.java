package com.kenzie.appserver.service;

import com.kenzie.appserver.config.ShipInformationCache;
import com.kenzie.appserver.repositories.ShipInformationRepository;
import com.kenzie.appserver.repositories.model.ShipInformationRecord;
import com.kenzie.appserver.service.model.ShipInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShipInformationServiceTest {
    private ShipInformationRepository shipInformationRepository;
    private ShipInformationCache shipInformationCache;
    private ShipInformationService shipInformationService;

    @BeforeEach
    void setup() {
        shipInformationRepository = mock(ShipInformationRepository.class);
        shipInformationCache = mock(ShipInformationCache.class);
        shipInformationService = new ShipInformationService(shipInformationRepository, shipInformationCache);
    }

    @Test
    void addShipInformation_validShipInformation_savesSuccessfully() {
        // GIVEN
        ShipInformation shipInformation = new ShipInformation("gameId", "playerCoordinates", "alienCoordinates", 1, 1);
        ArgumentCaptor<ShipInformationRecord> ShipInformationCaptor = ArgumentCaptor.forClass(ShipInformationRecord.class);

        // WHEN
        ShipInformation returnedShipInformation = shipInformationService.addShipInformation(shipInformation);
        Assertions.assertNotNull(returnedShipInformation);

        verify(shipInformationRepository).save(ShipInformationCaptor.capture());
        ShipInformationRecord record = ShipInformationCaptor.getValue();

        // THEN
        assertEquals(shipInformation.getGameId(), record.getGameId());
        assertEquals(shipInformation.getPlayerCoordinates(), record.getPlayerCoordinates());
        assertEquals(shipInformation.getAlienCoordinates(), record.getAlienCoordinates());
        assertEquals(shipInformation.getPlayerHealth(), record.getPlayerHealth());
        assertEquals(shipInformation.getAlienHealth(), record.getAlienHealth());
    }

    @Test
    void updateShipInformation_validShipInformation_updatedSuccessfully() {
        // GIVEN
        ShipInformation shipInformation = new ShipInformation("gameId", "playerCoordinates", "alienCoordinates", 1, 1);
        ArgumentCaptor<ShipInformationRecord> shipInformationCaptor = ArgumentCaptor.forClass(ShipInformationRecord.class);

        // WHEN
        when(shipInformationRepository.existsById("gameId")).thenReturn(true);
        shipInformationService.updateShipInformation(shipInformation);

        verify(shipInformationRepository).save(shipInformationCaptor.capture());
        ShipInformationRecord record = shipInformationCaptor.getValue();

        // THEN
        Assertions.assertNotNull(record, "The shipInformation record is returned");
        Assertions.assertEquals(record.getGameId(), shipInformation.getGameId());
        Assertions.assertEquals(record.getPlayerCoordinates(), shipInformation.getPlayerCoordinates());
        Assertions.assertEquals(record.getAlienCoordinates(), shipInformation.getAlienCoordinates());
        Assertions.assertEquals(record.getPlayerHealth(), shipInformation.getPlayerHealth());
        Assertions.assertEquals(record.getAlienHealth(), shipInformation.getAlienHealth());
    }

    @Test
    void getShipInformationById_validId_returnsShipInformation() {
        // GIVEN
        String shipInformationId = "gameId";

        ShipInformationRecord record = new ShipInformationRecord();
        record.setGameId(shipInformationId);
        record.setPlayerCoordinates("playerCoordinates");
        record.setAlienCoordinates("alienCoordinates");
        record.setPlayerHealth(1);
        record.setAlienHealth(1);

        // WHEN
        when(shipInformationRepository.findById(shipInformationId)).thenReturn(Optional.of(record));
        ShipInformation shipInformation = shipInformationService.getShipInformationById(shipInformationId);

        // THEN
        Assertions.assertNotNull(record, "The shipInformation record is returned");
        Assertions.assertEquals(record.getGameId(), shipInformation.getGameId());
        Assertions.assertEquals(record.getPlayerCoordinates(), shipInformation.getPlayerCoordinates());
        Assertions.assertEquals(record.getAlienCoordinates(), shipInformation.getAlienCoordinates());
        Assertions.assertEquals(record.getPlayerHealth(), shipInformation.getPlayerHealth());
        Assertions.assertEquals(record.getAlienHealth(), shipInformation.getAlienHealth());
    }

    @Test
    void deleteShipInformationById_deletesSuccessfully() {
        // GIVEN
        String shipInformationId = "gameId";
        ArgumentCaptor<String> shipInformationCaptor = ArgumentCaptor.forClass(String.class);

        // WHEN
        shipInformationService.deleteShipInformationById(shipInformationId);

        verify(shipInformationRepository).deleteById(shipInformationCaptor.capture());
        String deletedShipInformationId = shipInformationCaptor.getValue();

        // THEN
        Assertions.assertEquals(shipInformationId, deletedShipInformationId);
    }
}
