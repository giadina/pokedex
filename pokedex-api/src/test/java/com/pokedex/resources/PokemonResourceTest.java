package com.pokedex.resources;

import com.pokedex.models.responses.PokemonInfoResponse;
import com.pokedex.services.PokemonApi;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PokemonResourceTest {

    @Mock private PokemonApi pokemonApi;
    private PokemonResource resource;
    private PokemonInfoResponse basicPokemonInfoExpected;
    private PokemonInfoResponse yodaTranslatedPokemonInfoExpected;
    private PokemonInfoResponse shakespeareTranslatedPokemonInfoExpected;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        resource = new PokemonResource(pokemonApi);
        basicPokemonInfoExpected = createPokemonInfoResponse();
        yodaTranslatedPokemonInfoExpected = createYodaPokemonInfoResponse();
        shakespeareTranslatedPokemonInfoExpected = createShakespearePokemonInfoResponse();
    }

    @AfterEach
    public void clean() {
        reset(pokemonApi);
        reset(resource);
    }

    @Test
    public void retrieveBasicPokemonInfoSuccess() {
        String pokemonName = "mewtwo";

        when(pokemonApi.getPokemonInfo(pokemonName)).thenReturn(Optional.of(basicPokemonInfoExpected));
        PokemonInfoResponse pokemonInfoFound = resource.retrieveBasicPokemonInfo(pokemonName);
        assertEquals(basicPokemonInfoExpected, pokemonInfoFound);
        verify(pokemonApi).getPokemonInfo(pokemonName);
    }

    @Test
    public void retrieveTranslatedPokemonInfoYodaSuccess() {
        String pokemonName = "mewtwo";

        when(pokemonApi.getTranslatedPokemonInfo(pokemonName)).thenReturn(Optional.of(yodaTranslatedPokemonInfoExpected));
        PokemonInfoResponse pokemonInfoFound = resource.retrieveTranslatedPokemonInfo(pokemonName);
        assertEquals(yodaTranslatedPokemonInfoExpected, pokemonInfoFound);
        verify(pokemonApi).getTranslatedPokemonInfo(pokemonName);
    }

    @Test
    public void retrieveTranslatedPokemonInfoShakespeareSuccess() {
        String pokemonName = "pikachu";

        when(pokemonApi.getTranslatedPokemonInfo(pokemonName)).thenReturn(Optional.of(shakespeareTranslatedPokemonInfoExpected));
        PokemonInfoResponse pokemonInfoFound = resource.retrieveTranslatedPokemonInfo(pokemonName);
        assertEquals(shakespeareTranslatedPokemonInfoExpected, pokemonInfoFound);
        verify(pokemonApi).getTranslatedPokemonInfo(pokemonName);
    }

    @Test
    public void retrieveBasicPokemonInfoNotFound() {
        String pokemonName = "fakePokemonName";

        when(pokemonApi.getPokemonInfo(pokemonName)).thenThrow(new NotFoundException("The provided Pokemon doesn't exist"));
        Throwable exception = assertThrows(NotFoundException.class,() -> resource.retrieveBasicPokemonInfo(pokemonName));
        assertEquals("The provided Pokemon doesn't exist", exception.getMessage());
        verify(pokemonApi).getPokemonInfo(pokemonName);
    }

    @Test
    public void retrieveTranslatedPokemonInfoNotFound() {
        String pokemonName = "fakePokemonName";

        when(pokemonApi.getTranslatedPokemonInfo(pokemonName)).thenThrow(new NotFoundException("The provided Pokemon doesn't exist"));
        Throwable exception = assertThrows(NotFoundException.class,() -> resource.retrieveTranslatedPokemonInfo(pokemonName));
        assertEquals("The provided Pokemon doesn't exist", exception.getMessage());
        verify(pokemonApi).getTranslatedPokemonInfo(pokemonName);
    }

    private PokemonInfoResponse createPokemonInfoResponse() {
        String name = "mewtwo";
        String description = "It was created by a scientist after years of horrific gene " +
                "splicing and DNA engineering experiments.";
        String habitat = "rare";
        return new PokemonInfoResponse(name, true, habitat, description);
    }

    private PokemonInfoResponse createShakespearePokemonInfoResponse() {
        String name = "pikachu";
        String description = "At which hour several of these pok??mon gather,  their electricity couldst buildeth " +
                "and cause lightning storms.";
        String habitat = "forest";
        return new PokemonInfoResponse(name, false, habitat, description);
    }

    private PokemonInfoResponse createYodaPokemonInfoResponse() {
        String name = "mewtwo";
        String description = "Created by a scientist after years of horrific gene splicing and dna " +
                "engineering experiments,  it was.";
        String habitat = "rare";
        return new PokemonInfoResponse(name, true, habitat, description);
    }

}
