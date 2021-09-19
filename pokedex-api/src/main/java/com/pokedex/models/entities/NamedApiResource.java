package com.pokedex.models.entities;

public class NamedApiResource {
    private String name;
    private String url;

    public NamedApiResource() {}

    public NamedApiResource(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
