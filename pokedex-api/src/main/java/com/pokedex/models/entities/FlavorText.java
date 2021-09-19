package com.pokedex.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlavorText {
    @JsonProperty("flavor_text")
    private String flavorText;
    private NamedApiResource language;
    private NamedApiResource version;

    public FlavorText() {}

    public FlavorText(String flavorText, NamedApiResource language, NamedApiResource version) {
        this.flavorText = flavorText;
        this.language = language;
        this.version = version;
    }

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
