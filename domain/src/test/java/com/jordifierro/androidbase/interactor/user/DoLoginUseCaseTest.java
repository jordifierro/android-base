package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.entity.SessionEntity;
import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.repository.SessionRepository;
import com.jordifierro.androidbase.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DoLoginUseCaseTest {

    private static final String FAKE_EMAIL = "email@test.com";
    private static final String FAKE_PASS = "1234";

    private DoLoginUseCase doLoginUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doLoginUseCase = new DoLoginUseCase(mockThreadExecutor, mockPostExecutionThread,
                                            mockSessionRepository);
    }

    @Test
    public void testDeleteUserUseCaseSuccess() {

        doLoginUseCase.setParams(FAKE_EMAIL, FAKE_PASS);
        doLoginUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).loginUser(FAKE_EMAIL, FAKE_PASS);
        verifyNoMoreInteractions(mockSessionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}