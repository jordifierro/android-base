package com.jordifierro.androidbase.interactor.note;

import com.jordifierro.androidbase.entity.NoteEntity;
import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.user.CreateUserUseCase;
import com.jordifierro.androidbase.repository.NoteRepository;
import com.jordifierro.androidbase.repository.SessionRepository;
import com.jordifierro.androidbase.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CreateNoteUseCaseTest {

    private static final int FAKE_ID = 1;
    private static final String FAKE_TITLE = "MyTitle";
    private static final String FAKE_CONTENT = "MyContent";

    private CreateNoteUseCase createNoteUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private NoteRepository mockNoteRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        createNoteUseCase = new CreateNoteUseCase(mockThreadExecutor, mockPostExecutionThread,
                                                  mockNoteRepository, mockSessionRepository);
    }

    @Test
    public void testCreateNoteUseCaseSuccess() {
        NoteEntity note = new NoteEntity(FAKE_ID, FAKE_TITLE, FAKE_CONTENT);

        createNoteUseCase.setParams(note);
        createNoteUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockNoteRepository).createNote(null, note);
        verifyNoMoreInteractions(mockNoteRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}