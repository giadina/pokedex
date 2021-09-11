package com.pokedex.services;

import com.pokedex.models.Pokemon;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PokemonApi {

    Optional<Pokemon> getPokemonInfo(String pokemonName);

    Optional<Pokemon> getTranslatedPokemonInfo(String pokemonName);
}
