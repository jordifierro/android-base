package com.jordifierro.androidbase.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserEntityTest {

    private static final String FAKE_EMAIL = "email@test.com";

    private UserEntity user;

    @Before
    public void setup() {
        this.user = new UserEntity(FAKE_EMAIL);
    }

    @Test
    public void testUserConstructor() {
        assertThat(this.user.getEmail(), is(FAKE_EMAIL));
    }

    @Test
    public void testUserSetters() {
        this.user.setEmail("another@email.com");
        this.user.setAuthToken("1234TOKEN");

        assertThat(this.user.getEmail(), is("another@email.com"));
        assertThat(this.user.getAuthToken(), is("1234TOKEN"));
    }
}
