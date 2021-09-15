package com.pokedex.services;

import com.pokedex.models.responses.PokemonInfoResponse;

import java.util.Optional;

public interface PokemonApi {

    Optional<PokemonInfoResponse> getPokemonInfo(String pokemonName);

    Optional<PokemonInfoResponse> getTranslatedPokemonInfo(String pokemonName);
}
