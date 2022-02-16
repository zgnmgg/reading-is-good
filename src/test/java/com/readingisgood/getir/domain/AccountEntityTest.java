package com.readingisgood.getir.domain;

import com.readingisgood.getir.domain.model.AccountEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

public class AccountEntityTest {

    @Test
    public void modelMethodsTest() {
        AccountEntity model = new AccountEntity();

        model.setId(Long.valueOf("1"));
        model.setEmail("email");
        model.setPassword("password");
        model.setName("name");
        model.setRoles(null);

        MatcherAssert.assertThat(model.getId(), is(Long.valueOf("1")));
        MatcherAssert.assertThat(model.getEmail(), is("email"));
        MatcherAssert.assertThat(model.getName(), is("name"));
        MatcherAssert.assertThat(model.getPassword(), is("password"));
    }
}
