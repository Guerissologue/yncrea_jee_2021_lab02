package yncrea.lab02.utils;

import yncrea.lab02.core.dao.QuoteProvider;
import yncrea.lab02.core.service.QuoteService;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionUtils {

    public static QuoteProvider getQuoteProviderInstanceFromClassName(final String className){
        try {
            final Class<?> providerClass = Class.forName(className);
            return (QuoteProvider) providerClass.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static QuoteService buildQuoteService(String className, QuoteProvider provider) {
        try {
            final Class<?> providerClass = Class.forName(className);
            return (QuoteService) providerClass.getDeclaredConstructor(QuoteProvider.class).newInstance(provider);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
