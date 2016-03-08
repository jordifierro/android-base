package com.jordifierro.androidbase.data.net.wrapper;

import com.jordifierro.androidbase.data.net.error.ResponseErrorEntity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResponseErrorWrapperTest {

    private static final int FAKE_STATUS = 222;
    private static final String FAKE_MESSAGE = "Error message";

    private ResponseErrorWrapper responseErrorWrapper;

    @Before
    public void setup() {
        this.responseErrorWrapper =
                new ResponseErrorWrapper(new ResponseErrorEntity(FAKE_MESSAGE, FAKE_STATUS));
    }

    @Test
    public void testErrorWrapperConstructor() {
        assertThat(this.responseErrorWrapper.getError().getMessage(), is(FAKE_MESSAGE));
        assertThat(this.responseErrorWrapper.getError().getStatus(), is(FAKE_STATUS));
    }

    @Test
    public void testErrorWrapperSetError() {
        ResponseErrorEntity anotherError = new ResponseErrorEntity("Another message", 100);

        this.responseErrorWrapper.setError(anotherError);

        assertThat(this.responseErrorWrapper.getError().getMessage(), is("Another message"));
        assertThat(this.responseErrorWrapper.getError().getStatus(), is(100));
    }

}
