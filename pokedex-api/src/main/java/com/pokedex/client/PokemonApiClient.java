package com.pokedex.client;

import com.pokedex.models.entities.PokemonInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// This class is responsible for fetching Pokemon information data from
// "https://pokeapi.co/api/v2/pokemon-species/" endpoint
public class PokemonApiClient {
    private static final String URI = Constants.POKEMON_URL;
    private final Client client = ClientBuilder.newClient();

    public PokemonInfo getPokemon(String pokemonName) {
        Response response = null;
        try {
            response = client.target(URI).path(pokemonName).request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                return response.readEntity(PokemonInfo.class);
            }
        } finally {
            assert response != null;
            response.close();
        }
        return null;
    }
}
