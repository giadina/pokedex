package com.pokedex.services;

import com.pokedex.client.PokemonApiClient;
import com.pokedex.client.ShakespeareTranslatorClient;
import com.pokedex.client.YodaTranslatorClient;
import com.pokedex.models.entities.PokemonInfo;

import javax.inject.Inject;

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
    public PokemonInfo getPokemonInfo(String pokemonName) {
        PokemonInfo pokemonInfo = pokemonApiClient.getPokemon(pokemonName);
        if(pokemonInfo != null) {
            return pokemonInfo;
        }

        return null;
    }

    @Override
    public PokemonInfo getTranslatedPokemonInfo(String pokemonName) {
        PokemonInfo pokemonInfo = pokemonApiClient.getPokemon(pokemonName);

        if(pokemonInfo != null) {
            String habitatName = pokemonInfo.getHabitat().getName().toLowerCase();
            String description = pokemonInfo.getHabitat();

            if(habitatName.equals("cave") || pokemonInfo.isLegendary()) {
                yodaTranslator.getYodaTranslation(description);
            } else {
                shakespeareTranslator.getShakespeareTranslation(description);
            }
            return pokemonInfo;
        }
        return null;
    }
}
