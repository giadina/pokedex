package com.pokedex.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class YodaTranslatorClient {
    private static final String URI = Constants.YODA_TRANSLATOR_URL;

    private Client client = ClientBuilder.newClient();

    public Response getYodaTranslation(String text) {
        return client.target(URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(text, MediaType.APPLICATION_JSON));
    }
}
