package com.jordifierro.androidbase.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.utils.TestUtils;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class VersionDataRepositoryTest {

    private static final String FAKE_EMAIL = "fake@mail.com";
    private static final String AUTH_TOKEN = "fake_auth_token";

    private VersionDataRepository versionDataRepository;
    private TestObserver testObserver;
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
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(RestApi.class)
        );
        this.testObserver = new TestObserver();
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
                .subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/versions/state", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
    }

    @Test
    public void testCheckVersionExpirationOk() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/version_expiration_ok.json"))));

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                                    .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        VersionEntity responseVersion =
                (VersionEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertEquals(VersionEntity.VERSION_OK, responseVersion.getState());
    }

    @Test
    public void testCheckVersionExpirationWarned() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/version_expiration_warned.json"))));

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        VersionEntity responseVersion =
                (VersionEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertEquals(VersionEntity.VERSION_WARNED, responseVersion.getState());
    }

    @Test
    public void testCheckVersionExpirationExpired() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/version_expiration_expired.json"))));

        this.versionDataRepository.checkVersionExpiration(this.fakeUser)
                .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        VersionEntity responseVersion =
                (VersionEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertEquals(VersionEntity.VERSION_EXPIRED, responseVersion.getState());
    }

}
