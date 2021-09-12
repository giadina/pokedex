package com.pokedex.models.entities;

import lombok.Getter;

@Getter
public class Payload {
    private String text;

    public Payload(String text) {
        this.text = text;
    }
}
