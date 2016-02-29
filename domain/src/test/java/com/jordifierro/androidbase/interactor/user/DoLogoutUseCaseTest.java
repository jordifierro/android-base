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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DoLogoutUseCaseTest {

    private DoLogoutUseCase doLogoutUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doLogoutUseCase = new DoLogoutUseCase(mockThreadExecutor, mockPostExecutionThread,
                                              mockSessionRepository);
    }

    @Test
    public void testDeleteUserUseCaseSuccess() {

        doLogoutUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verify(mockSessionRepository).logoutUser(null);
        verifyNoMoreInteractions(mockSessionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}