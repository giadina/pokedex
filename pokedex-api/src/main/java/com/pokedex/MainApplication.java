package com.pokedex;

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
    public void run(final MainConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

