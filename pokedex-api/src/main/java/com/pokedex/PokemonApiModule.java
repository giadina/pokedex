package com.pokedex;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pokedex.client.PokemonApiClient;
import com.pokedex.services.PokemonApi;
import com.pokedex.services.PokemonApiImpl;

// This class is used to manage the Dependency Injection bindings
public class PokemonApiModule extends AbstractModule {

    private final MetricRegistry metricRegistry;

    public PokemonApiModule(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    protected void configure() {
        bind(PokemonApiClient.class).in(Scopes.SINGLETON);
        bind(PokemonApi.class).to(PokemonApiImpl.class).asEagerSingleton();
    }
}
