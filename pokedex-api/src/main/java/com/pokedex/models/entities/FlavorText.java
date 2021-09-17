package com.pokedex.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlavorText {
    @JsonProperty("flavor_text")
    private String flavorText;
    private NamedApiResource language;
    private NamedApiResource version;

    public String getFlavorText() {
        return flavorText;
    }

    public NamedApiResource getLanguage() {
        return language;
    }

    public NamedApiResource getVersion() {
        return version;
    }


}
