package com.pokedex.client;

import com.pokedex.models.entities.Payload;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// This class is responsible for fetching Yoda translation data from
// "https://api.funtranslations.com/translate/yoda.json" endpoint, appending test as query param
public class YodaTranslatorClient {
    private static String URI = Constants.YODA_TRANSLATOR_URL;

    private Client client = ClientBuilder.newClient();

    public Response getYodaTranslation(String text) {
        Payload payload = new Payload(text);
        return client.target(URI).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(payload, MediaType.APPLICATION_JSON));
    }
}
