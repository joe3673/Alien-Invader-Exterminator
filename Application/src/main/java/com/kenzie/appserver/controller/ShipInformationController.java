package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ShipInformationCreateRequest;
import com.kenzie.appserver.controller.model.ShipInformationResponse;
import com.kenzie.appserver.controller.model.ShipInformationUpdateRequest;
import com.kenzie.appserver.service.ShipInformationService;
import com.kenzie.appserver.service.model.ShipInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/shipinformation")
public class ShipInformationController {
    private ShipInformationService shipInformationService;


    // TODO Constructor
    ShipInformationController(ShipInformationService shipInformationService) {
        this.shipInformationService = shipInformationService;
    }


    // TODO Methods
    @PostMapping
    public ResponseEntity<ShipInformationResponse> addShipInformation(@RequestBody ShipInformationCreateRequest shipInformationCreateRequest) {
        ShipInformation shipInformation = new ShipInformation(shipInformationCreateRequest.getPlayerCoordinates(), shipInformationCreateRequest.getAlienCoordinates());
        shipInformationService.addShipInformation(shipInformation);

        ShipInformationResponse shipInformationResponse = createShipInformationResponse(shipInformation);

        return ResponseEntity.created(URI.create("/shipinformation/" + shipInformationResponse.getGameId())).body(shipInformationResponse);
    }

    @PutMapping
    public ResponseEntity<ShipInformationResponse> updateShipInformation(@RequestBody ShipInformationUpdateRequest shipInformationUpdateRequest) {
        ShipInformation shipInformation = new ShipInformation(shipInformationUpdateRequest.getGameId(),
                shipInformationUpdateRequest.getPlayerCoordinates(),
                shipInformationUpdateRequest.getAlienCoordinates(),
                shipInformationUpdateRequest.getPlayerHealth(),
                shipInformationUpdateRequest.getAlienHealth());
        shipInformationService.updateShipInformation(shipInformation);

        ShipInformationResponse shipInformationResponse = createShipInformationResponse(shipInformation);

        return ResponseEntity.ok(shipInformationResponse);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<ShipInformationResponse> getShipInformationById(@PathVariable("gameId") String gameId) {
        ShipInformation shipInformation = shipInformationService.getShipInformationById(gameId);

        // If there is no ShipInformation, then return a 204
        if (shipInformation == null) {
            return ResponseEntity.notFound().build();
        }

        // Otherwise, convert it into a ShipInformationResponse and return it
        ShipInformationResponse shipInformationResponse = createShipInformationResponse(shipInformation);
        return ResponseEntity.ok(shipInformationResponse);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity deleteShipInformationById(@PathVariable("gameId") String gameId) {
        shipInformationService.deleteShipInformationById(gameId);
        return ResponseEntity.noContent().build();
    }

    private ShipInformationResponse createShipInformationResponse(ShipInformation shipInformation){
        ShipInformationResponse shipInformationResponse = new ShipInformationResponse();

        shipInformationResponse.setGameId(shipInformation.getGameId());
        shipInformationResponse.setPlayerCoordinates(shipInformation.getPlayerCoordinates());
        shipInformationResponse.setAlienCoordinates(shipInformation.getAlienCoordinates());
        shipInformationResponse.setPlayerHealth(shipInformation.getPlayerHealth());
        shipInformationResponse.setAlienHealth(shipInformation.getAlienHealth());

        return shipInformationResponse;
    }
}
