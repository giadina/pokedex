package com.pokedex.resources;

import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import com.pokedex.models.responses.PokemonInfoResponse;
import com.pokedex.services.PokemonApi;
import io.dropwizard.jersey.caching.CacheControl;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;


@Path("/pokemon")
@Api("/pokemon")
@SwaggerDefinition(tags = { @Tag(name = "Pokemon", description = "Pokemon Resource") })
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class PokemonResource {
    private static final Logger logger = LoggerFactory.getLogger(PokemonResource.class);
    @Inject private PokemonApi pokemonApi;

    @GET
    @Path("/{pokemon_name}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    @Timed(name = "pokedex_api.pokemon_info.basic.metrics")
    @ResponseMetered(name = "pokedex_api.pokemon_info.basic.http_responses")
    @ApiOperation(value = "Retrieve basic pokemon information", response = PokemonInfoResponse.class)
    @ApiResponses(value =
            {@ApiResponse(code = HttpStatus.OK_200, message = "Basic Info Successfully Returned", response = PokemonInfoResponse.class),
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "Basic Info Not Found for this Pokemon")})
    public PokemonInfoResponse retrieveBasicPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {

        logger.info("Calling /pokemon with pokemon name: {}", pokemonName);
        return pokemonApi.getPokemonInfo(pokemonName)
                .orElseThrow(() -> new NotFoundException("The provided Pokemon doesn't exist"));
    }


    @GET
    @Path("/translated/{pokemon_name}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    @Timed(name = "pokedex_api.pokemon_info.translated.metrics")
    @ResponseMetered(name = "pokedex_api.pokemon_info.translated.http_responses")
    @ApiOperation(value = "Retrieve translated pokemon information", response = PokemonInfoResponse.class)
    @ApiResponses(value =
            {@ApiResponse(code = HttpStatus.OK_200, message = "Translated Info Successfully Returned", response = PokemonInfoResponse.class),
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "Translated Info Not Found for this Pokemon")})
    public PokemonInfoResponse retrieveTranslatedPokemonInfo(@NotNull @PathParam("pokemon_name") String pokemonName) {

        logger.info("Calling /pokemon/translated with pokemon name: {}", pokemonName);
        return pokemonApi.getTranslatedPokemonInfo(pokemonName)
                .orElseThrow(() -> new NotFoundException("The provided Pokemon doesn't exist"));
    }

}
