package com.jordifierro.androidbase.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VersionEntityTest {

    private static final String FAKE_MSG = "fake message";

    private VersionEntity message;

    @Before
    public void setup() {
        this.message = new VersionEntity(FAKE_MSG);
    }

    @Test
    public void testMessageConstructor() {
        assertThat(this.message.getExpirationDate(), is(FAKE_MSG));
    }

    @Test
    public void testUserSetters() {
        this.message.setExpirationDate("another message");

        assertThat(this.message.getExpirationDate(), is("another message"));
    }

}
