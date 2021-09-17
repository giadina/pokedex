package com.pokedex.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonInfo {
    private int id;
    private String name;
    @JsonProperty("is_legendary")
    private boolean isLegendary;
    private NamedApiResource habitat;
    @JsonProperty("flavor_text_entries")
    private List<FlavorText> flavorTextEntries;

    public PokemonInfo(int id, String name, boolean isLegendary, NamedApiResource habitat, List<FlavorText> flavorTextEntries) {
        this.id = id;
        this.name = name;
        this.isLegendary = isLegendary;
        this.habitat = habitat;
        this.flavorTextEntries = flavorTextEntries;
    }

    public PokemonInfo() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public NamedApiResource getHabitat() {
        return habitat;
    }

    public List<FlavorText> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    // This function will return an English Description if it exists,
    // otherwise it will return a random one. If none are present, it will return null object
    public String getPokemonDescription(List<FlavorText> flavorTexts) {
        FlavorText flavorText = flavorTexts.stream()
                .filter(text -> "en".equals(text.getLanguage().getName()))
                .findAny()
                .orElseGet(() -> getRandomFlavorText(flavorTexts));

        if(flavorText.getFlavorText() != null) {
            return flavorText.getFlavorText().replaceAll("(\\r|\\n|\\f)+", " ");
        };
        return null;
    }

    private FlavorText getRandomFlavorText(List<FlavorText> flavorTexts) {
        return flavorTexts.stream().findAny().orElse(null);
    }
}
