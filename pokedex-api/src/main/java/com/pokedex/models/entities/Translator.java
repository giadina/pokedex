package com.pokedex.models.entities;

import javax.ws.rs.core.Response;

public interface Translator {

    String getTranslation(String description);
}
