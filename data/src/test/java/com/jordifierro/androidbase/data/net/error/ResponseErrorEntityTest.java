package com.jordifierro.androidbase.data.net.error;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResponseErrorEntityTest {

    private static final int FAKE_STATUS = 100;
    private static final String FAKE_MESSAGE = "Error message";

    private ResponseErrorEntity error;

    @Before
    public void setup() {
        this.error =  new ResponseErrorEntity(FAKE_MESSAGE, FAKE_STATUS);
    }

    @Test
    public void tesErrorConstructor() {
        assertThat(this.error.getMessage(), is(FAKE_MESSAGE));
        assertThat(this.error.getStatus(), is(FAKE_STATUS));
    }

    @Test
    public void testNoteSetters() {

        this.error.setMessage("Another message");
        this.error.setStatus(200);

        assertThat(this.error.getMessage(), is("Another message"));
        assertThat(this.error.getStatus(), is(200));
    }

}
