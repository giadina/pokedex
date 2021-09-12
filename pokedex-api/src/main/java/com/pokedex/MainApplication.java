package com.pokedex;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pokedex.resources.PokemonResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.DefaultJaxrsScanner;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

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
        // Swagger ui
        final AssetsBundle assetsBundle = new
                AssetsBundle("/swagger-ui", "/swagger-ui", "index.html");
        bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(final MainConfiguration configuration, final Environment environment) {
        Injector guiceInjector = Guice.createInjector(new PokemonApiModule());
        environment.jersey().register(PokemonResource.class);
        environment.jersey().register(guiceInjector.getInstance(PokemonResource.class));
        this.initSwagger(configuration, environment);
    }

    private void initSwagger(MainConfiguration configuration, Environment environment) {
        // Swagger Resource
        environment.jersey().register(new ApiListingResource());
        environment.jersey().register(SwaggerSerializers.class);

        Package objPackage = this.getClass().getPackage();
        String version = objPackage.getImplementationVersion();

        // Swagger Scanner to find all the resources annotated with @Api
        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(version);
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:8080");
        beanConfig.setPrettyPrint(true);
        beanConfig.setDescription("Pokedex api specification");
        beanConfig.setResourcePackage(PokemonResource.class.getPackage().getName());
        beanConfig.setScan(true);
    }
}

