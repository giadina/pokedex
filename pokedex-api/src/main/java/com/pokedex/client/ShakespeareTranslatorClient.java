package com.pokedex.client;

import com.pokedex.models.entities.Payload;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// This class is responsible for fetching Shakespeare translation data from
// "https://api.funtranslations.com/translate/shakespeare.json" endpoint, appending test as query param
public class ShakespeareTranslatorClient {
    private static final String URI = Constants.SHAKESPEARE_TRANSLATOR_URL;

    private Client client = ClientBuilder.newClient();

    public Response getShakespeareTranslation(String text) {
        Payload payload = new Payload(text);
        return client.target(URI).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(payload, MediaType.APPLICATION_JSON));
    }
}
