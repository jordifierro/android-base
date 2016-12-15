package com.jordifierro.androidbase.domain.interactor.note;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.NoteRepository;
import com.jordifierro.androidbase.domain.repository.SessionRepository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetNotesUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private NoteRepository mockNoteRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testGetNotesUseCaseSuccess() {
        GetNotesUseCase getNotesUseCase = new GetNotesUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockNoteRepository, mockSessionRepository);
        TestObserver<List<NoteEntity>> testObserver = new TestObserver<>();
        List<NoteEntity> notes = Arrays.asList(new NoteEntity[]
                { new NoteEntity("t1", "c1"), new NoteEntity("t2", "c2")});
        given(mockNoteRepository.getNotes(any(UserEntity.class)))
                .willReturn(Observable.just(notes));

        getNotesUseCase.buildUseCaseObservable().subscribe(testObserver);

        Assert.assertEquals(notes.size(),
                ((List<NoteEntity>)(testObserver.getEvents().get(0)).get(0)).size());
        Assert.assertEquals(notes.get(1).getContent(),
                ((List<NoteEntity>)(testObserver.getEvents().get(0)).get(0)).get(1).getContent());
        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockNoteRepository).getNotes(null);
        verifyNoMoreInteractions(mockNoteRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}