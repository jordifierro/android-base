package com.jordifierro.androidbase.data.net.wrapper;

import com.jordifierro.androidbase.domain.entity.UserEntity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserWrapperTest {

    private static final String FAKE_EMAIL = "email@test.com";

    private UserWrapper userWrapper;

    @Before
    public void setup() {
        this.userWrapper =  new UserWrapper(new UserEntity(FAKE_EMAIL));
    }

    @Test
    public void testUserWrapperConstructor() {
        assertThat(this.userWrapper.getUser().getEmail(), is(FAKE_EMAIL));
    }

    @Test
    public void testUserWrapperSetUser() {
        UserEntity anotherUser = new UserEntity("another@mail.com");

        this.userWrapper.setUser(anotherUser);

        assertThat(this.userWrapper.getUser().getEmail(), is("another@mail.com"));
    }

}
