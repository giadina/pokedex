package com.pokedex.services;

import com.pokedex.models.responses.PokemonInfoResponse;

public interface PokemonApi {

    PokemonInfoResponse getPokemonInfo(String pokemonName);

    PokemonInfoResponse getTranslatedPokemonInfo(String pokemonName);
}
