package com.pokedex.services;

import com.pokedex.models.Pokemon;
import java.util.Optional;

public class PokemonApiImpl implements PokemonApi {

    @Override
    public Optional<Pokemon> getPokemonInfo(String pokemonName) {
        // this function will call the http api to fetch pokemon info
        return Optional.empty();
    }

    @Override
    public Optional<Pokemon> getTranslatedPokemonInfo(String pokemonName) {
        // this function will call the http api to fetch pokemon info
        // with translation
        return Optional.empty();
    }
}
