package com.jordifierro.androidbase.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.utils.TestUtils;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.DefaultThreadExecutor;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SuppressWarnings("unchecked")
public class NoteDataRepositoryTest {

    private MockWebServer mockWebServer;
    private NoteDataRepository noteDataRepository;
    private TestSubscriber testSubscriber;

    @Mock UserEntity mockUser;
    @Mock NoteEntity mockNote;

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
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
                        .create(RestApi.class)
        );
        this.testSubscriber = new TestSubscriber();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        this.mockWebServer.shutdown();
    }

    @Test
    public void testCreateNoteSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(201).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_create_ok.json"))));

        this.noteDataRepository.createNote(mockUser, mockNote)
                                    .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                                    .observeOn(Schedulers.io())
                                    .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        NoteEntity responseNote = (NoteEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseNote.getId() > 0);
        assertThat(responseNote.getTitle().length(), is(not(0)));
        assertThat(responseNote.getContent().length(), is(not(0)));
    }

    @Test
    public void testCreateNoteError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(422).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_create_error.json"))));

        this.noteDataRepository.createNote(mockUser, mockNote)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        assertThat(this.testSubscriber.getOnErrorEvents().size(), is(1));
    }

    @Test
    public void testGetNoteSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_get_ok.json"))));


        this.noteDataRepository.getNote(mockUser, 1)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        NoteEntity responseNote = (NoteEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseNote.getId() > 0);
        assertThat(responseNote.getTitle().length(), is(not(0)));
        assertThat(responseNote.getContent().length(), is(not(0)));
    }

    @Test
    public void testGetNoteError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_get_error.json"))));


        this.noteDataRepository.getNote(mockUser, 1)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        assertThat(this.testSubscriber.getOnErrorEvents().size(), is(1));
    }

    @Test
    public void testGetNotesSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_getall_ok.json"))));

        this.noteDataRepository.getNotes(mockUser)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        List<NoteEntity> responseNotes = (List<NoteEntity>) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseNotes.size() > 0);
        assertThat(responseNotes.get(0).getTitle().length(), is(not(0)));
        assertThat(responseNotes.get(0).getContent().length(), is(not(0)));
    }

    @Test
    public void testGetNotesError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.noteDataRepository.getNotes(mockUser)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        assertThat(this.testSubscriber.getOnErrorEvents().size(), is(1));
    }

    @Test
    public void testUpdateNoteSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_update_ok.json"))));

        this.noteDataRepository.updateNote(mockUser, mockNote)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        NoteEntity responseNote = (NoteEntity) this.testSubscriber.getOnNextEvents().get(0);
        assertTrue(responseNote.getId() > 0);
        assertThat(responseNote.getTitle().length(), is(not(0)));
        assertThat(responseNote.getContent().length(), is(not(0)));
    }

    @Test
    public void testUpdateNoteError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(404).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/note_update_error.json"))));

        this.noteDataRepository.updateNote(mockUser, mockNote)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        assertThat(this.testSubscriber.getOnErrorEvents().size(), is(1));
    }

    @Test
    public void testDeleteNoteSuccess() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        this.noteDataRepository.deleteNote(mockUser, 1)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(1);
    }

    @Test
    public void testDeleteUserError() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.noteDataRepository.deleteNote(mockUser, 1)
                .subscribeOn(Schedulers.from(new DefaultThreadExecutor()))
                .observeOn(Schedulers.io())
                .subscribe(this.testSubscriber);
        this.testSubscriber.awaitTerminalEvent();

        this.testSubscriber.assertValueCount(0);
        assertThat(this.testSubscriber.getOnErrorEvents().size(), is(1));
    }

}
