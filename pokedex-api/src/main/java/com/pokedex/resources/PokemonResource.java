package com.pokedex.resources;

import com.pokedex.models.Pokemon;
import com.pokedex.services.PokemonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;


@Path("/pokemon")
// Add swagger annotation in pom as well
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class PokemonResource {
    private static final Logger logger = LoggerFactory.getLogger(PokemonResource.class);
    @Inject private PokemonApi pokemonApi;

    @GET
    @Path("/{pokemon_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveBasicPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {
        try {
            logger.info("Calling /pokemon with pokemon name: {}", pokemonName);
            Optional<Pokemon> pokemon = pokemonApi.getPokemonInfo(pokemonName);
            return "hello";
        } catch (Exception e) {
            logger.warn("The provided Pokemon name doesn't exist");
            return "The provided Pokemon name doesn't exist";
        }
    }


    @GET
    @Path("/translated/{pokemon_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveTranslatedPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {
        try {
            logger.info("Calling /pokemon/translated with pokemon name: {}", pokemonName);
            Optional<Pokemon> pokemon = pokemonApi.getTranslatedPokemonInfo(pokemonName);
            return "hello";
        } catch (Exception e){
            logger.warn("The provided Pokemon name doesn't exist");
            return "The provided Pokemon name doesn't exist";
        }
    }

}
