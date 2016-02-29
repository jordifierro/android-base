package com.jordifierro.androidbase.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SessionEntityTest {

    private static final String FAKE_EMAIL = "email@test.com";
    private static final String FAKE_TOKEN = "1234ABCD";

    private SessionEntity session;

    @Before
    public void setup() {
        this.session =  new SessionEntity(FAKE_EMAIL, FAKE_TOKEN);
    }

    @Test
    public void testSessionConstructor() {
        assertThat(this.session.getEmail(), is(FAKE_EMAIL));
        assertThat(this.session.getToken(), is(FAKE_TOKEN));
    }

    @Test
    public void testUserSetEmail() {
        this.session.setEmail("another@email.com");
        this.session.setToken("anotherTOKEN");

        assertThat(this.session.getEmail(), is("another@email.com"));
        assertThat(this.session.getToken(), is("anotherTOKEN"));
    }
}
