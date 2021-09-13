package com.pokedex.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@ApiModel(description = "Pokemon Info Model.")
@AllArgsConstructor
public class PokemonInfoResponse {
    private String name;
    @JsonProperty("isLegendary")
    private boolean isLegendary;
    private String habitat;
    private String description;

}
