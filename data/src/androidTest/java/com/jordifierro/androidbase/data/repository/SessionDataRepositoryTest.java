package com.jordifierro.androidbase.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.InstrumentationTestCase;

import com.jordifierro.androidbase.domain.entity.UserEntity;

public class SessionDataRepositoryTest extends InstrumentationTestCase {

    private static final String SHARED_PACKAGE = "SharedPreferencesTest";
    private static final String FAKE_EMAIL = "test@email.com";
    private static final String FAKE_TOKEN = "1234TOKEN";

    private SharedPreferences sharedPreferences;
    private SessionDataRepository sessionDataRepository;
    private UserEntity user;

    protected void setUp() throws Exception {
        this.sharedPreferences = getInstrumentation().getTargetContext()
                .getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        this.sessionDataRepository = new SessionDataRepository(this.sharedPreferences);
        this.user = new UserEntity(FAKE_EMAIL);
        this.user.setAuthToken(FAKE_TOKEN);
    }

    public void testGetWithoutSetReturnsNull(){
        assertNull(this.sessionDataRepository.getCurrentUser().getEmail());
        assertNull(this.sessionDataRepository.getCurrentUser().getAuthToken());
    }

    public void testSetAndGetSettedUser() {
        this.sessionDataRepository.setCurrentUser(this.user);
        UserEntity currentUser = this.sessionDataRepository.getCurrentUser();

        assertEquals(currentUser.getEmail(), FAKE_EMAIL);
        assertEquals(currentUser.getAuthToken(), FAKE_TOKEN);
    }

    public void testSetInvalidateAndGetNullUser() {
        this.sessionDataRepository.setCurrentUser(this.user);
        this.sessionDataRepository.invalidateSession();

        assertNull(this.sessionDataRepository.getCurrentUser().getEmail());
        assertNull(this.sessionDataRepository.getCurrentUser().getAuthToken());
    }
}
