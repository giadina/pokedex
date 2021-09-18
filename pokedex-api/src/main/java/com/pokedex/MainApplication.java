package com.pokedex;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pokedex.models.entities.TranslatorFactory;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application<MainConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(PokemonResource.class);

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
        Injector guiceInjector = Guice.createInjector(new PokemonApiModule(environment.metrics()));
        environment.jersey().register(PokemonResource.class);
        environment.jersey().register(guiceInjector.getInstance(PokemonResource.class));
        this.initSwagger(configuration, environment);
        TranslatorFactory.setGuiceInjector(guiceInjector);
        this.startReport(environment.metrics());
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

    private void startReport(MetricRegistry metricRegistry) {
        SharedMetricRegistries.add("pokemonResourceRegistry", metricRegistry);

        File dir = new File( "../metrics");
        if(! dir.exists() && ! dir.mkdirs()) {
            logger.error("Cannot create directory " + dir.getAbsoluteFile());
        }
        final CsvReporter reporter = CsvReporter.forRegistry(metricRegistry)
                .formatFor(Locale.ENGLISH)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File("../metrics"));

        reporter.start(1, TimeUnit.MINUTES);
    }
}

