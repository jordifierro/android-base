package com.jordifierro.androidbase.domain.interactor.user;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CreateUserUseCaseTest {

    private static final String FAKE_PASS = "1234";

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;
    @Mock private SessionRepository mockSessionRepository;
    @Mock private UserEntity mockUser;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateUserUseCaseSuccess() {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockUserRepository, mockSessionRepository);
        TestScheduler testScheduler = new TestScheduler();
        given(mockUserRepository.createUser(mockUser))
                .willReturn(Observable.just(mockUser));

        createUserUseCase.setParams(mockUser);
        createUserUseCase.buildUseCaseObservable()
                .observeOn(testScheduler)
                .subscribe(new TestSubscriber<UserEntity>());
        testScheduler.triggerActions();

        verify(mockUserRepository).createUser(mockUser);
        verifyNoMoreInteractions(mockUserRepository);
        verify(mockSessionRepository).setCurrentUser(mockUser);
        verifyNoMoreInteractions(mockSessionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}