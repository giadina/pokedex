package com.pokedex.services;

import com.pokedex.models.entities.PokemonInfo;

public interface PokemonApi {

    PokemonInfo getPokemonInfo(String pokemonName);

    PokemonInfo getTranslatedPokemonInfo(String pokemonName);
}
