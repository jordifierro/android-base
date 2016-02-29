package com.jordifierro.androidbase.interactor.note;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.repository.NoteRepository;
import com.jordifierro.androidbase.repository.SessionRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetNotesUseCaseTest {

    private GetNotesUseCase getNotesUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private NoteRepository mockNoteRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getNotesUseCase = new GetNotesUseCase(mockThreadExecutor, mockPostExecutionThread,
                                              mockNoteRepository, mockSessionRepository);
    }

    @Test
    public void testGetNotesUseCaseSuccess() {

        getNotesUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockNoteRepository).getNotes(null);
        verifyNoMoreInteractions(mockNoteRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}