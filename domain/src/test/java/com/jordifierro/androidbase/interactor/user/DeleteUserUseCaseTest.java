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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DeleteUserUseCaseTest {

    private DeleteUserUseCase deleteUserUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        deleteUserUseCase = new DeleteUserUseCase(mockThreadExecutor, mockPostExecutionThread,
                                                  mockUserRepository, mockSessionRepository);
    }

    @Test
    public void testDeleteUserUseCaseSuccess() {

        deleteUserUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockUserRepository).deleteUser(null);
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}