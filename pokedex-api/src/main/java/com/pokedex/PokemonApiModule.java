package com.pokedex;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pokedex.client.PokemonApiClient;
import com.pokedex.client.ShakespeareTranslatorClient;
import com.pokedex.client.YodaTranslatorClient;
import com.pokedex.services.PokemonApi;
import com.pokedex.services.PokemonApiImpl;

public class PokemonApiModule extends AbstractModule {

    public PokemonApiModule() {}

    @Override
    protected void configure() {
        bind(PokemonApiClient.class).in(Scopes.SINGLETON);
        bind(ShakespeareTranslatorClient.class).in(Scopes.SINGLETON);
        bind(YodaTranslatorClient.class).in(Scopes.SINGLETON);
        bind(PokemonApi.class).to(PokemonApiImpl.class).asEagerSingleton();
    }
}
