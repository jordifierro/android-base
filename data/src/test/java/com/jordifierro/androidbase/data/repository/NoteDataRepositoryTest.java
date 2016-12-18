package com.jordifierro.androidbase.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.data.utils.TestUtils;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

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
import static junit.framework.Assert.assertTrue;

@SuppressWarnings("unchecked")
public class NoteDataRepositoryTest {

    private static final String AUTH_TOKEN = "fake_auth_token";
    private static final String NOTE_TITLE = "fake_note_title";
    private static final String NOTE_CONTENT = "fake_note_content";
    private static final int NOTE_ID = 3;

    private MockWebServer mockWebServer;
    private NoteDataRepository noteDataRepository;
    private TestObserver testObserver;

    private UserEntity fakeUser;
    private NoteEntity fakeNote;

    @Before
    public void setUp() throws IOException {
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();

        this.noteDataRepository = new NoteDataRepository(
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

        this.fakeUser = new UserEntity("some@mail");
        this.fakeUser.setAuthToken(AUTH_TOKEN);
        this.fakeNote = new NoteEntity(NOTE_ID, NOTE_TITLE, NOTE_CONTENT);
    }

    @After
    public void tearDown() throws Exception {
        this.mockWebServer.shutdown();
    }

    @Test
    public void testCreateNoteRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.noteDataRepository.createNote(this.fakeUser, this.fakeNote)
                .subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/notes", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals(new Gson().toJson(this.fakeNote).toString(), request.getBody().readUtf8());
    }

    @Test
    public void testCreateNoteSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_create_ok.json"))));

        this.noteDataRepository.createNote(this.fakeUser, this.fakeNote)
                .subscribeWith(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        NoteEntity responseNote =
                (NoteEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertTrue(responseNote.getId() > 0);
        assertTrue(responseNote.getTitle().length() > 0);
        assertTrue(responseNote.getContent().length() > 0);
    }

    @Test
    public void testCreateNoteErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(422).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_create_error.json"))));

        this.noteDataRepository.createNote(this.fakeUser, this.fakeNote)
                .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException) this.testObserver.errors().get(0);
        assertEquals(422, error.getStatusCode());
        assertEquals("Title can't be blank", error.getMessage());
    }

    @Test
    public void testGetNoteRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.noteDataRepository.getNote(this.fakeUser, this.fakeNote.getId())
                .subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/notes/" + this.fakeNote.getId(), request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals("", request.getBody().readUtf8());
    }

    @Test
    public void testGetNoteSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_get_ok.json"))));


        this.noteDataRepository.getNote(this.fakeUser, 1).subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        NoteEntity responseNote =
                (NoteEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertTrue(responseNote.getId() > 0);
        assertTrue(responseNote.getTitle().length() > 0);
        assertTrue(responseNote.getContent().length() > 0);
    }

    @Test
    public void testGetNoteErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_get_error.json"))));


        this.noteDataRepository.getNote(this.fakeUser, 1).subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException)
                this.testObserver.errors().get(0);
        assertEquals(404, error.getStatusCode());
        assertEquals("not found.", error.getMessage());
    }

    @Test
    public void testCreateNotesRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.noteDataRepository.getNotes(this.fakeUser).subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/notes", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals("", request.getBody().readUtf8());
    }

    @Test
    public void testGetNotesSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_getall_ok.json"))));

        this.noteDataRepository.getNotes(this.fakeUser).subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        List<NoteEntity> responseNotes =
                (List<NoteEntity>) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertTrue(responseNotes.size() > 0);
        assertTrue(responseNotes.get(0).getTitle().length() > 0);
        assertTrue(responseNotes.get(0).getContent().length() > 0);
    }

    @Test
    public void testGetNotesErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.noteDataRepository.getNotes(this.fakeUser).subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException) testObserver.errors().get(0);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().length() > 0);
    }

    @Test
    public void testUpdateNoteRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.noteDataRepository.updateNote(this.fakeUser, this.fakeNote)
                .subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/notes/" + this.fakeNote.getId(), request.getPath());
        assertEquals("PUT", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals(new Gson().toJson(this.fakeNote).toString(), request.getBody().readUtf8());
    }

    @Test
    public void testUpdateNoteSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_update_ok.json"))));

        this.noteDataRepository.updateNote(this.fakeUser, this.fakeNote)
                .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        NoteEntity responseNote =
                (NoteEntity) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertTrue(responseNote.getId() > 0);
        assertTrue(responseNote.getTitle().length() > 0);
        assertTrue(responseNote.getContent().length() > 0);
    }

    @Test
    public void testUpdateNoteErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_update_error.json"))));

        this.noteDataRepository.updateNote(this.fakeUser, this.fakeNote)
                .subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException) testObserver.errors().get(0);
        assertEquals(404, error.getStatusCode());
        assertEquals("not found.", error.getMessage());
    }

    @Test
    public void testDeleteNoteRequest() throws Exception {
        this.mockWebServer.enqueue(new MockResponse());

        this.noteDataRepository.deleteNote(this.fakeUser, this.fakeNote.getId())
                .subscribe(this.testObserver);

        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("/notes/" + this.fakeNote.getId(), request.getPath());
        assertEquals("DELETE", request.getMethod());
        assertEquals(AUTH_TOKEN, request.getHeader("Authorization"));
        assertEquals("", request.getBody().readUtf8());
    }

    @Test
    public void testDeleteNoteSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        this.noteDataRepository.deleteNote(this.fakeUser, 1).subscribeWith(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertTerminated();
    }

    @Test
    public void testDeleteUserErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.noteDataRepository.deleteNote(this.fakeUser, 1).subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException) testObserver.errors().get(0);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().length() > 0);
    }

}
