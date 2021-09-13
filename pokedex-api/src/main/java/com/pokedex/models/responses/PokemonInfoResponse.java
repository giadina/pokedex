package com.pokedex.models.responses;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@ApiModel(description = "Pokemon Info Model.")
@AllArgsConstructor
public class PokemonInfoResponse {
    private String name;
    private boolean isLegendary;
    private String habitat;
    private String description;

}
