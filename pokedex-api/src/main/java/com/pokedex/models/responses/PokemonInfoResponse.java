package com.pokedex.models.responses;

import io.swagger.annotations.ApiModel;


@ApiModel(description = "Pokemon Info Model.")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
