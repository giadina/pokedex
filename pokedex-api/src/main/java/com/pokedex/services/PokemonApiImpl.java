package com.pokedex.services;

import com.pokedex.client.Constants;
import com.pokedex.client.PokemonApiClient;
import com.pokedex.models.entities.PokemonInfo;
import com.pokedex.models.entities.Translator;
import com.pokedex.models.entities.TranslatorFactory;
import com.pokedex.models.responses.PokemonInfoResponse;

import javax.inject.Inject;
import java.util.Optional;

public class PokemonApiImpl implements PokemonApi {
    private final PokemonApiClient pokemonApiClient;

    @Inject
    public PokemonApiImpl(PokemonApiClient pokemonApiClient) {
        this.pokemonApiClient = pokemonApiClient;
    }

    @Override
    public Optional<PokemonInfoResponse> getPokemonInfo(String pokemonName) {
        PokemonInfo pokemonInfo = pokemonApiClient.getPokemon(pokemonName);
        return mapPokemonInfoToResponseInfo(pokemonInfo);
    }


    @Override
    public Optional<PokemonInfoResponse> getTranslatedPokemonInfo(String pokemonName) {
        Optional<PokemonInfoResponse> pokemonInfo = getPokemonInfo(pokemonName);

        if(pokemonInfo.isPresent()) {
            String translation = computeTranslation(pokemonInfo.get());
            pokemonInfo.get().setDescription(translation);
            return pokemonInfo;
        }
        return Optional.empty();
    }

    private String computeTranslation(PokemonInfoResponse pokemonInfo) {
        String habitatName = pokemonInfo.getHabitat().toLowerCase();
        String description = pokemonInfo.getDescription();
        Translator translator;

        if(habitatName.equals("cave") || pokemonInfo.isLegendary()) {
            translator = TranslatorFactory.getTranslator(Constants.YODA_TRANSLATOR_NAME);
        } else {
            translator = TranslatorFactory.getTranslator(Constants.SHAKESPEARE_TRANSLATOR_NAME);
        }

        return translator.getTranslation(description);
    }

    private Optional<PokemonInfoResponse> mapPokemonInfoToResponseInfo(PokemonInfo pokemonInfo) {
        if(pokemonInfo == null) return Optional.empty();

        String description = pokemonInfo.getPokemonDescription(pokemonInfo.getFlavorTextEntries());
        return Optional.of(new PokemonInfoResponse(
                pokemonInfo.getName(),
                pokemonInfo.isLegendary(),
                pokemonInfo.getHabitat().getName(),
                description
        ));
    }
}
