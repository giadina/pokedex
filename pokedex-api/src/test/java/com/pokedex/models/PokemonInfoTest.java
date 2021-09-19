package com.pokedex.models;

import com.pokedex.models.entities.FlavorText;
import com.pokedex.models.entities.NamedApiResource;
import com.pokedex.models.entities.PokemonInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PokemonInfoTest {

    @Mock
    private PokemonInfo pokemonInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void clean() {
        reset(pokemonInfo);
    }

    @Test
    public void getEnglishPokemonDescriptionTest() {
        List<FlavorText> flavorTexts = getFlavorTexts();
        String expectedDescription = "It is very hot­ headed by nature, so it constantly seeks opponents. It calms down only when it wins.";
        
        when(pokemonInfo.getPokemonDescription(flavorTexts)).thenReturn(expectedDescription);
        assertEquals(expectedDescription, pokemonInfo.getPokemonDescription(flavorTexts));
        verify(pokemonInfo).getPokemonDescription(flavorTexts);
    }

    @Test
    public void getRandomPokemonDescriptionTest() {
        List<FlavorText> flavorTexts = getNonEnglishFlavorTexts();
        String expectedDescription = "Charmeleon distrugge il nemico senza pietà con i suoi artigli affilati. " +
                "Quando incontra un avversario molto forte diventa aggressivo. In questo stato di grande agitazione la fiamma della coda diventa di colore bianco bluastro.";

        when(pokemonInfo.getPokemonDescription(flavorTexts)).thenReturn(expectedDescription);
        assertEquals(expectedDescription, pokemonInfo.getPokemonDescription(flavorTexts));
        verify(pokemonInfo).getPokemonDescription(flavorTexts);
    }

    private List<FlavorText> getFlavorTexts() {
        FlavorText f1 = new FlavorText("Charmeleon distrugge il nemico senza pietà con i suoi\nartigli affilati. " +
                "Quando incontra un avversario molto forte\ndiventa aggressivo. In questo stato di grande agitazione\nla fiamma della coda diventa di colore bianco bluastro.",
                new NamedApiResource("it","https://pokeapi.co/api/v2/language/8/"),
                new NamedApiResource("omega-ruby", "https://pokeapi.co/api/v2/version/25/"));

        FlavorText f2 = new FlavorText("It is very hot­\nheaded by nature,\nso it constantly\fseeks opponents.\nIt calms down only\nwhen it wins.",
                new NamedApiResource("en","https://pokeapi.co/api/v2/language/9/"),
                new NamedApiResource("gold", "https://pokeapi.co/api/v2/version/4/"));

        FlavorText f3 = new FlavorText("La nuit, la queue ardente du Reptincel\nbrille comme une étoile dans son\nrepaire montagneux.",
                new NamedApiResource("fr","https://pokeapi.co/api/v2/language/5/"),
                new NamedApiResource("black", "https://pokeapi.co/api/v2/version/17/"));

        FlavorText f4 = new FlavorText("燃える　尻尾を　振り回すと\nまわりの　温度が　どんどん　上がって\n相手を　苦しめる。",
                new NamedApiResource("ja","https://pokeapi.co/api/v2/language/11/"),
                new NamedApiResource("y", "https://pokeapi.co/api/v2/version/24/"));

        FlavorText f5 = new FlavorText("When it swings\nits burning tail,\nit elevates the\ftemperature to\nunbearably high\nlevels.",
                new NamedApiResource("en","https://pokeapi.co/api/v2/language/9/"),
                new NamedApiResource("red", "https://pokeapi.co/api/v2/version/1/"));

        return new ArrayList<>(Arrays.asList(f1, f2, f3, f4, f5));
    }

    private List<FlavorText> getNonEnglishFlavorTexts() {
        List<FlavorText> flavorTexts = getFlavorTexts();
        return flavorTexts.stream().filter(text -> !text.getLanguage().getName().equals("en")).collect(Collectors.toList());
    }
}
