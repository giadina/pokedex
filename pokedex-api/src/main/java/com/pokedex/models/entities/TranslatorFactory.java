package com.pokedex.models.entities;

import com.google.inject.Injector;
import com.pokedex.client.Constants;
import com.pokedex.client.ShakespeareTranslatorClient;
import com.pokedex.client.YodaTranslatorClient;

public class TranslatorFactory {

    private static Injector guiceInjector;

    public static Translator getTranslator(String translatorName) {

        if(translatorName == null) {
            return null;
        }
        if(translatorName.equalsIgnoreCase(Constants.YODA_TRANSLATOR_NAME)){
            return guiceInjector.getInstance(YodaTranslatorClient.class);
        }
        if(translatorName.equalsIgnoreCase(Constants.SHAKESPEARE_TRANSLATOR_NAME)){
            return guiceInjector.getInstance(ShakespeareTranslatorClient.class);
        }

        return null;
    }

    public static void setGuiceInjector(Injector guiceInjector) {
        TranslatorFactory.guiceInjector = guiceInjector;
    }
}
