package com.jordifierro.androidbase.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MessageEntityTest {

    private static final String FAKE_MSG = "fake message";

    private MessageEntity message;

    @Before
    public void setup() {
        this.message = new MessageEntity(FAKE_MSG);
    }

    @Test
    public void testMessageConstructor() {
        assertThat(this.message.getMessage(), is(FAKE_MSG));
    }

    @Test
    public void testUserSetters() {
        this.message.setMessage("another message");

        assertThat(this.message.getMessage(), is("another message"));
    }

}
