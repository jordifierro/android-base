package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CreateUserUseCaseTest {

    private static final String FAKE_EMAIL = "email@test.com";
    private static final String FAKE_PASS = "1234";

    private CreateUserUseCase createUserUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        createUserUseCase = new CreateUserUseCase(mockThreadExecutor, mockPostExecutionThread,
                                                  mockUserRepository);
    }

    @Test
    public void testCreateUserUseCaseSuccess() {

        createUserUseCase.setParams(FAKE_EMAIL, FAKE_PASS, FAKE_PASS);
        createUserUseCase.buildUseCaseObservable();

        verify(mockUserRepository).createUser(FAKE_EMAIL, FAKE_PASS, FAKE_PASS);
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}