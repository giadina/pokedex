package com.pokedex.services;

import com.pokedex.client.PokemonApiClient;
import com.pokedex.client.ShakespeareTranslatorClient;
import com.pokedex.client.YodaTranslatorClient;
import com.pokedex.models.entities.PokemonInfo;
import com.pokedex.models.responses.PokemonInfoResponse;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Map;


public class PokemonApiImpl implements PokemonApi {
    private final PokemonApiClient pokemonApiClient;
    private final ShakespeareTranslatorClient shakespeareTranslator;
    private final YodaTranslatorClient yodaTranslator;

    @Inject
    public PokemonApiImpl(PokemonApiClient pokemonApiClient,
                          ShakespeareTranslatorClient shakespeareTranslator,
                          YodaTranslatorClient yodaTranslator) {
        this.pokemonApiClient = pokemonApiClient;
        this.shakespeareTranslator = shakespeareTranslator;
        this.yodaTranslator = yodaTranslator;
    }


    @Override
    public PokemonInfoResponse getPokemonInfo(String pokemonName) {
        PokemonInfo pokemonInfo = pokemonApiClient.getPokemon(pokemonName);

        if(pokemonInfo != null) {
            PokemonInfoResponse response = mapPokemonInfoToResponseInfo(pokemonInfo);
            return response;
        }

        return null;
    }


    @Override
    public PokemonInfoResponse getTranslatedPokemonInfo(String pokemonName) {
        PokemonInfoResponse pokemonInfo = getPokemonInfo(pokemonName);

        if(pokemonInfo != null) {
            String translation = computeTranslation(pokemonInfo);
            pokemonInfo.setDescription(translation);
            return pokemonInfo;
        }
        return null;
    }

    private String computeTranslation(PokemonInfoResponse pokemonInfo) {
        String habitatName = pokemonInfo.getHabitat().toLowerCase();
        String description = pokemonInfo.getDescription();
        String translation = description;
        Response response;
        if(habitatName.equals("cave") || pokemonInfo.isLegendary()) {
            response = yodaTranslator.getYodaTranslation(description);
        } else {
            response = shakespeareTranslator.getShakespeareTranslation(description);
        }

        if(response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            Map<String, Map<String, Object>> json = response.readEntity(new GenericType<Map<String, Map<String, Object>>>() {});
            translation = json.get("contents").get("translated").toString();
        }
        return translation;
    }

    private PokemonInfoResponse mapPokemonInfoToResponseInfo(PokemonInfo pokemonInfo) {
        String description = pokemonInfo.getPokemonDescription(pokemonInfo.getFlavorTextEntries());

        return new PokemonInfoResponse(
                pokemonInfo.getName(),
                pokemonInfo.isLegendary(),
                pokemonInfo.getHabitat().getName(),
                description
        );
    }
}
