package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.GetNotesUseCase;
import com.jordifierro.androidbase.presentation.view.NotesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class NotesPresenterTest {

    @Mock GetNotesUseCase getNotesUseCase;
    @Mock NotesView mockNotesView;
    @Mock Observable mockObservable;

    private NotesPresenter notesPresenter;
    private NotesPresenter.NotesSubscriber notesSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.notesPresenter = new NotesPresenter(this.getNotesUseCase);
        this.notesPresenter.initWithView(this.mockNotesView);
        this.notesSubscriber = this.notesPresenter.new NotesSubscriber();
    }

    @Test
    public void testDestroy() {

        this.notesPresenter.destroy();

        verify(getNotesUseCase).unsubscribe();
    }

    @Test
    public void testGetNotes() throws Exception {

        verify(this.mockNotesView).showLoader();
        verify(this.getNotesUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.notesSubscriber.onCompleted();

        verify(this.mockNotesView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.notesSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showError(any(String.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.notesSubscriber.onNext(new ArrayList<NoteEntity>());

        verify(this.mockNotesView).showLoader();
        verify(this.mockNotesView).hideLoader();
        verify(this.mockNotesView).showNotes(any(List.class));
    }

}
