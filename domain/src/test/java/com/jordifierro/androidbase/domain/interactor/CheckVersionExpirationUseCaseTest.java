package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.VersionRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CheckVersionExpirationUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private VersionRepository mockVersionRepository;
    @Mock private SessionRepository mockSessionRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testGetNotesUseCaseSuccess() {
        CheckVersionExpirationUseCase checkVersionExpirationUseCase =
                new CheckVersionExpirationUseCase(mockThreadExecutor, mockPostExecutionThread,
                                                mockVersionRepository, mockSessionRepository);

        checkVersionExpirationUseCase.buildUseCaseObservable();

        verify(mockSessionRepository).getCurrentUser();
        verifyNoMoreInteractions(mockSessionRepository);
        verify(mockVersionRepository).checkVersionExpiration(null);
        verifyNoMoreInteractions(mockVersionRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}