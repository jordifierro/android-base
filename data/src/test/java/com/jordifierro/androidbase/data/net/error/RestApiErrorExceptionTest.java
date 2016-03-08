package com.jordifierro.androidbase.data.net.error;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestApiErrorExceptionTest {

    private static final String FAKE_MESSAGE = "Error message";
    private static final int FAKE_STATUS = 100;

    private RestApiErrorException restApiErrorException;

    @Before
    public void setup() {
        this.restApiErrorException = new RestApiErrorException(FAKE_MESSAGE, FAKE_STATUS);
    }

    @Test
    public void testRestApiErrorConstructor() {
        assertThat(this.restApiErrorException.getMessage(), is(FAKE_MESSAGE));
        assertThat(this.restApiErrorException.getStatusCode(), is(FAKE_STATUS));
    }

    @Test
    public void testStatusCodes() {
        assertEquals(400, RestApiErrorException.BAD_REQUEST);
        assertEquals(401, RestApiErrorException.UNAUTHORIZED);
        assertEquals(404, RestApiErrorException.NOT_FOUND);
        assertEquals(422, RestApiErrorException.UNPROCESSABLE_ENTITY);
        assertEquals(426, RestApiErrorException.UPGRADE_REQUIRED);
        assertEquals(500, RestApiErrorException.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testRestApiErrorSetStatus() {

        this.restApiErrorException.setStatusCode(300);

        assertThat(this.restApiErrorException.getStatusCode(), is(300));
    }

}
