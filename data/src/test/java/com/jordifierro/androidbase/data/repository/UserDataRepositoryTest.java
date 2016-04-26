package com.jordifierro.androidbase.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.data.net.wrapper.UserWrapper;
import com.jordifierro.androidbase.data.utils.TestUtils;
import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SuppressWarnings("unchecked")
public class UserDataRepositoryTest {

    private static final String FAKE_EMAIL = "fake@mail.com";
    private static final String FAKE_PASS = "1234";
    private static final String FAKE_CONFPASS = "5678";
    private static final String AUTH_TOKEN = "fake_auth_token";

    private UserDataRepository userDataRepository;
    private TestSubscriber testSubscriber;
    private MockWebServer mockWebServer;
    private UserEntity fakeUser;
    private Gson gson;

    @Before
    public void setUp() throws IOException {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();

        this.userDataRepository = new UserDataRepository(
                new Retrofit.Builder()
                        .baseUrl(mockWebServer.url("/"))
                        .addConverterFactory(GsonConverterFactory.create(this.gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
                        .create(RestApi.class)
        );

        this.testSubscriber = new TestSubscriber();

        this.fakeUser = new UserEntity(FAKE_EMAIL);
        this.fakeUser.setPassword(FAKE_PASS);
        this.fakeUser.setPasswordConfirmation(FAKE_CONFPASS);
        this.fakeUser.setAuthToken(AUTH_TOKEN);
    }

    @After
    public void tearDown() throws Exception {
        this.mockWebServer.shutdown();
    }

    @Test
    public void testCreateUserRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.userDataRepository.createUser(this.fakeUser).subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/users", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(this.gson.toJson(new UserWrapper(this.fakeUser)).toString(),
                     request.getBody().readUtf8());
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/user_create_ok.json"))));

        this.userDataRepository.createUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        UserEntity responseUser = (UserEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseUser.getEmail().length() > 0);
        assertTrue(responseUser.getAuthToken().length() > 0);
    }

    @Test
    public void testCreateUserError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(422).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/user_create_error.json"))));

        this.userDataRepository.createUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testSubscriber.getOnErrorEvents().get(0);
        assertEquals(422, error.getStatusCode());
        assertEquals("Email has already been taken", error.getMessage());
    }

    @Test
    public void testDeleteUserRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.userDataRepository.deleteUser(this.fakeUser).subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/users/0", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals("", request.getBody().readUtf8());
    }

    @Test
    public void testDeleteUserSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        this.userDataRepository.deleteUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(1);
    }

    @Test
    public void testDeleteUserError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.userDataRepository.deleteUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testSubscriber.getOnErrorEvents().get(0);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().length() > 0);
    }

    @Test
    public void testResetPasswordRequest() throws Exception {
        this.fakeUser.setNewPassword("new_password");
        this.fakeUser.setNewPasswordConfirmation("new_password_confirmation");
        this.mockWebServer.enqueue(new MockResponse());

        this.userDataRepository.resetPassword(this.fakeUser).subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/users/reset_password", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(this.gson.toJson(new UserWrapper(this.fakeUser)).toString(),
                     request.getBody().readUtf8());
    }

    @Test
    public void testResetPasswordSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/reset_password_ok.json"))));

        this.userDataRepository.resetPassword(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        MessageEntity responseMessage =
                (MessageEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertEquals("Check your email to confirm the new password", responseMessage.getMessage());
    }

    @Test
    public void testResetPasswordError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(422).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/reset_password_error.json"))));

        this.userDataRepository.resetPassword(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testSubscriber.getOnErrorEvents().get(0);
        assertEquals(422, error.getStatusCode());
        assertEquals("New password confirmation doesn't match New password", error.getMessage());
    }

    @Test
    public void testLoginUserRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.userDataRepository.loginUser(this.fakeUser).subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/users/login", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(new UserWrapper(this.fakeUser)).toString(),
                     request.getBody().readUtf8());
    }

    @Test
    public void testLoginUserSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/session_login_ok.json"))));


        this.userDataRepository.loginUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        UserEntity responseUser = (UserEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseUser.getEmail().length() > 0);
        assertTrue(responseUser.getAuthToken().length() > 0);
    }

    @Test
    public void testLoginUserError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(422).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/session_login_error.json"))));


        this.userDataRepository.loginUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testSubscriber.getOnErrorEvents().get(0);
        assertEquals(422, error.getStatusCode());
        assertEquals("Invalid email or password.", error.getMessage());
    }

    @Test
    public void testLogoutUserRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.userDataRepository.logoutUser(this.fakeUser).subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/users/logout", request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals("", request.getBody().readUtf8());
    }

    @Test
    public void testLogoutUserSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        this.userDataRepository.logoutUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(1);
    }

    @Test
    public void testLogoutUserError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.userDataRepository.logoutUser(this.fakeUser).subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testSubscriber.getOnErrorEvents().get(0);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().length() > 0);
    }

}
