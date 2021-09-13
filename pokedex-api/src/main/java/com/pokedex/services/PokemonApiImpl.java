package com.pokedex.services;

import com.pokedex.client.Constants;
import com.pokedex.client.PokemonApiClient;
import com.pokedex.models.entities.PokemonInfo;
import com.pokedex.models.entities.Translator;
import com.pokedex.models.entities.TranslatorFactory;
import com.pokedex.models.responses.PokemonInfoResponse;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Map;


public class PokemonApiImpl implements PokemonApi {
    private final PokemonApiClient pokemonApiClient;

    @Inject
    public PokemonApiImpl(PokemonApiClient pokemonApiClient) {
        this.pokemonApiClient = pokemonApiClient;
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
        Translator translator;

        if(habitatName.equals("cave") || pokemonInfo.isLegendary()) {
            translator = TranslatorFactory.getTranslator(Constants.YODA_TRANSLATOR_NAME);
        } else {
            translator = TranslatorFactory.getTranslator(Constants.SHAKESPEARE_TRANSLATOR_NAME);
        }

        response = translator.getTranslation(description);
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
