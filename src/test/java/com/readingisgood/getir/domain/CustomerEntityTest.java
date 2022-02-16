package com.readingisgood.getir.domain;

import com.readingisgood.getir.domain.model.CustomerEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

public class CustomerEntityTest {

    @Test
    public void modelMethodsTest() {
        CustomerEntity model = new CustomerEntity();

        model.setEmail("email");
        model.setFirstName("first");
        model.setLastName("last");
        model.setPhoneNumber("123");
        model.setAddress("adres");

        MatcherAssert.assertThat(model.getEmail(), is("email"));
        MatcherAssert.assertThat(model.getFirstName(), is("first"));
        MatcherAssert.assertThat(model.getLastName(), is("last"));
        MatcherAssert.assertThat(model.getPhoneNumber(), is("123"));
        MatcherAssert.assertThat(model.getAddress(), is("adres"));
    }
}
