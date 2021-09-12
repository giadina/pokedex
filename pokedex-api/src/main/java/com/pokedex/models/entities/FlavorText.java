package com.pokedex.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FlavorText {
    @JsonProperty("flavor_text")
    private String flavorText;
    private NamedApiResource language;
    private NamedApiResource version;

}
