package com.pokedex.models.responses;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PokemonInfoResponse {
    private String name;
    private boolean isLegendary;
    private String habitat;
    private String description;

    public PokemonInfoResponse(String name, boolean isLegendary, String habitat, String description) {
        this.name = name;
        this.isLegendary = isLegendary;
        this.habitat = habitat;
        this.description = description;
    }
}
