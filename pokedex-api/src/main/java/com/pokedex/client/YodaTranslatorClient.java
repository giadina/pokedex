package com.pokedex.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// This class is responsible for fetching Yoda translation data from
// "https://api.funtranslations.com/translate/yoda.json" endpoint, appending test as query param
public class YodaTranslatorClient {
    private static String URI = Constants.YODA_TRANSLATOR_URL;

    private Client client = ClientBuilder.newClient();

    public Response getYodaTranslation(String text) {
        return client.target(URI).queryParam("text", text).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(text, MediaType.APPLICATION_JSON));
    }
}
