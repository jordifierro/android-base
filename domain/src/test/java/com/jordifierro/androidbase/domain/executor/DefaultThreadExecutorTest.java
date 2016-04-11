package com.jordifierro.androidbase.domain.executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DefaultThreadExecutorTest {

    private DefaultThreadExecutor defaultThreadExecutor;

    @Mock private Runnable mockCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.defaultThreadExecutor = new DefaultThreadExecutor();
    }

    @Test
    public void testExecute() {

        defaultThreadExecutor.execute(mockCommand);

        verify(this.mockCommand).run();
    }

}
