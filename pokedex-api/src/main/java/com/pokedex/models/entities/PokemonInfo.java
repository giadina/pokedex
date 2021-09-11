package com.pokedex.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokedex.models.entities.FlavorText;
import com.pokedex.models.entities.NamedApiResource;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonInfo {
    private int id;
    private String name;
    private boolean isLegendary;
    private NamedApiResource habitat;
    private List<FlavorText> flavorTextEntries;


    public String getEnglishDescription(List<FlavorText> flavorTexts) {
        FlavorText flavorText = flavorTexts.stream()
                .filter(text -> "en".equals(text.getLanguage().getName()))
                .findAny()
                .orElseGet(() -> getRandomFlavorText(flavorTexts));

        if(flavorText.getFlavor_text() != null) return flavorText.getFlavor_text();
        return null;
    }

    private FlavorText getRandomFlavorText(List<FlavorText> flavorTexts) {
        return flavorTexts.stream().findAny().orElse(null);
    }
}
