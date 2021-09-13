package com.pokedex.models.entities;

import javax.ws.rs.core.Response;

public interface Translator {

    Response getTranslation(String description);
}
