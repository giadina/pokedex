package com.pokedex;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pokedex.resources.PokemonResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MainApplication extends Application<MainConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "Pokedex REST API service";
    }

    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MainConfiguration configuration, final Environment environment) {
        Injector guiceInjector = Guice.createInjector(new PokemonApiModule());
        environment.jersey().register(PokemonResource.class);
        environment.jersey().register(guiceInjector.getInstance(PokemonResource.class));
    }

}

