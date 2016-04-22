package com.jordifierro.androidbase.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.utils.TestUtils;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.executor.DefaultThreadExecutor;

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
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SuppressWarnings("unchecked")
public class VersionDataRepositoryTest {

    private static final String FAKE_EMAIL = "fake@mail.com";
    private static final String AUTH_TOKEN = "fake_auth_token";

    private VersionDataRepository versionDataRepository;
    private TestSubscriber testSubscriber;
    private MockWebServer mockWebServer;
    private UserEntity fakeUser;

    @Before
    public void setUp() throws IOException {
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();
        this.versionDataRepository = new VersionDataRepository(
                new Retrofit.Builder()
                        .baseUrl(mockWebServer.url("/"))
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
                        .create(RestApi.class)
        );
        this.testSubscriber = new TestSubscriber();
        this.fakeUser = new UserEntity(FAKE_EMAIL);
        this.fakeUser.setAuthToken(AUTH_TOKEN);
    }

    @After
    public void tearDown() throws Exception {
        this.mockWebServer.shutdown();
    }

    @Test
    public void testCheckVersionExpirationRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/versions/expiration", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
    }

    @Test
    public void testCheckVersionExpirationSet() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/version_expiration_set.json"))));

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                                    .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                                    .observeOn(Schedulers.io())
                                    .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        VersionEntity responseVersion =
                (VersionEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseVersion.getExpirationDate().length() > 0);
    }

    @Test
    public void testCheckVersionExpirationUnset() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/version_expiration_unset.json"))));

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        VersionEntity responseVersion =
                (VersionEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseVersion.getExpirationDate().length() == 0);
    }

}
