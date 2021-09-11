package com.pokedex.models.entities;

import lombok.Getter;

@Getter
public class FlavorText {
    private String flavor_text;
    private NamedApiResource language;
    private NamedApiResource version;

}
