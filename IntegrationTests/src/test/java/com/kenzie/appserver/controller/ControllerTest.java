package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;

import com.kenzie.appserver.controller.model.ShipInformationCreateRequest;
import com.kenzie.appserver.service.ShipInformationService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.service.model.ShipInformation;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class ShipInformationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ShipInformationService exampleService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {
        String randomPlayerCoordinates = mockNeat.strings().valStr();
        String randomAlienCoordinates = mockNeat.strings().valStr();

        ShipInformation example = new ShipInformation(randomPlayerCoordinates, randomAlienCoordinates);
        ShipInformation persistedExample = exampleService.addShipInformation(example);
        mvc.perform(get("/{gameId}", persistedExample.getGameId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("playerCoordinates")
                        .value(is(randomPlayerCoordinates)))
                .andExpect(jsonPath("alienCoordinates")
                        .value(is(randomAlienCoordinates)))
                .andExpect(status().isOk());
    }

    @Test
    public void createShip_CreateSuccessful() throws Exception {
        String name = mockNeat.strings().valStr();

        ShipInformationCreateRequest exampleCreateRequest = new ShipInformationCreateRequest();


        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/shipinformation/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(exampleCreateRequest)))
                .andExpect(jsonPath("gameId")
                        .exists())
                .andExpect(status().isCreated());
    }
}