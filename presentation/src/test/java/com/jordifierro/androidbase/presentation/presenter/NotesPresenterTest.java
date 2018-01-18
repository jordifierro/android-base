package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.interactor.CheckVersionExpirationUseCase;
import com.jordifierro.androidbase.domain.interactor.note.GetNotesUseCase;
import com.jordifierro.androidbase.presentation.view.NotesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class NotesPresenterTest {

    @Mock
    GetNotesUseCase getNotesUseCase;
    @Mock
    CheckVersionExpirationUseCase checkVersionExpirationUseCase;
    @Mock
    NotesView mockNotesView;

    private NotesPresenter notesPresenter;
    private NotesPresenter.NotesSubscriber notesSubscriber;
    private NotesPresenter.VersionExpirationSubscriber versionExpirationSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.notesPresenter = new NotesPresenter(mockNotesView, this.checkVersionExpirationUseCase, this.getNotesUseCase
        );
        this.notesPresenter.create();
        this.notesSubscriber = this.notesPresenter.new NotesSubscriber();
        this.versionExpirationSubscriber = this.notesPresenter.new VersionExpirationSubscriber();
    }

    @Test
    public void testDestroy() {

        this.notesPresenter.destroy();

        verify(this.getNotesUseCase).unsubscribe();
        verify(this.checkVersionExpirationUseCase).unsubscribe();
        assertNull(this.notesPresenter.view());
    }

    @Test
    public void testGetNotes() throws Exception {

        this.notesPresenter.resume();

        verify(this.mockNotesView).showLoader();
        verify(this.getNotesUseCase).execute(any(BasePresenter.BaseSubscriber.class));
        verify(this.checkVersionExpirationUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.notesSubscriber.onComplete();

        verify(this.mockNotesView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.notesSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.notesSubscriber.onNext(new ArrayList<NoteEntity>());

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showNotes(any(List.class));
    }

    @Test
    public void testVersionSubscriberOnNextWithDate() {
        VersionEntity versionEntity = new VersionEntity(VersionEntity.VERSION_WARNED);

        this.versionExpirationSubscriber.onNext(versionEntity);

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showExpirationWarning();
    }

    @Test
    public void testVersionSubscriberOnNextWithoutDate() {
        VersionEntity versionEntity = new VersionEntity();

        this.versionExpirationSubscriber.onNext(versionEntity);

        verify(this.mockNotesView).hideLoader();
        verifyNoMoreInteractions(this.mockNotesView);
    }

}
