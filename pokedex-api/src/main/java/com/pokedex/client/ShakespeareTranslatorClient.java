package com.pokedex.client;

import com.pokedex.models.entities.Payload;
import com.pokedex.models.entities.Translator;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

// This class is responsible for fetching Shakespeare translation data from
// "https://api.funtranslations.com/translate/shakespeare.json" endpoint, appending test as query param
public class ShakespeareTranslatorClient implements Translator {

    private static final String URI = Constants.SHAKESPEARE_TRANSLATOR_URL;
    private final Client client = ClientBuilder.newClient();

    @Override
    public String getTranslation(String text) {
        Response response = null;

        try {
            Payload payload = new Payload(text);
            response = client.target(URI).request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(payload, MediaType.APPLICATION_JSON));

            if(response.getStatus() == 200) {
                Map<String, Map<String, Object>> json = response.readEntity(new GenericType<Map<String, Map<String, Object>>>() {});
                return json.get("contents").get("translated").toString();
            }
        } catch (RuntimeException exception) {
            return text;
        }

        return text;
    }
}
