package com.jordifierro.androidbase.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VersionEntityTest {

    private static final String FAKE_EXPIRATION_DATE = "02/02/2002";

    private VersionEntity version;

    @Before
    public void setup() {
        this.version = new VersionEntity(FAKE_EXPIRATION_DATE);
    }

    @Test
    public void testMessageConstructor() {
        assertThat(this.version.getState(), is(FAKE_EXPIRATION_DATE));
    }

    @Test
    public void testUserSetters() {
        this.version.setState("11/11/1111");

        assertThat(this.version.getState(), is("11/11/1111"));
    }

}
