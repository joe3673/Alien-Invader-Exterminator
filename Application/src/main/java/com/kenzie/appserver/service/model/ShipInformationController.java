package com.kenzie.appserver.service.model;

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
        ShipInformation shipInformation = new ShipInformation(shipInformationCreateRequest.getGameId(),
                shipInformationCreateRequest.getPlayerCoordinates(),
                shipInformationCreateRequest.getAlienCoordinates(),
                shipInformationCreateRequest.getPlayerHealth(),
                shipInformationCreateRequest.getAlienHealth());
                shipInformation.setRound(0);
                shipInformation.setStartTime(System.currentTimeMillis());
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
