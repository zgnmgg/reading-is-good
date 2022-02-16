package com.readingisgood.getir.config;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

public class LocalResolverTest {

    @Test
    public void getDefaultLocaleTest() {
        LocaleResolver lr = new LocaleResolver();

        MatcherAssert.assertThat(lr.getDefaultLocale().getLanguage(), is("tr"));
    }

    @Test
    public void getSupportedLocalesTest() {
        LocaleResolver lr = new LocaleResolver();

        MatcherAssert.assertThat(lr.getSupportedLocales().size(), is(2));
    }
}
