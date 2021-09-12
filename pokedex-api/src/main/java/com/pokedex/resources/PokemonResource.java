package com.pokedex.resources;

import com.pokedex.models.responses.PokemonInfoResponse;
import com.pokedex.services.PokemonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/pokemon")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class PokemonResource {
    private static final Logger logger = LoggerFactory.getLogger(PokemonResource.class);
    @Inject private PokemonApi pokemonApi;

    @GET
    @Path("/{pokemon_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonInfoResponse retrieveBasicPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {

        logger.info("Calling /pokemon with pokemon name: {}", pokemonName);
        PokemonInfoResponse pokemonInfo = pokemonApi.getPokemonInfo(pokemonName);
        if(pokemonInfo != null) {
            return pokemonInfo;
        }

        logger.warn("The provided Pokemon doesn't exist");
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }


    @GET
    @Path("/translated/{pokemon_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public PokemonInfoResponse retrieveTranslatedPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {

        logger.info("Calling /pokemon/translated with pokemon name: {}", pokemonName);
        PokemonInfoResponse pokemonInfo = pokemonApi.getTranslatedPokemonInfo(pokemonName);
        if(pokemonInfo != null) {
            return pokemonInfo;
        }

        logger.warn("The provided Pokemon doesn't exist");
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

}
