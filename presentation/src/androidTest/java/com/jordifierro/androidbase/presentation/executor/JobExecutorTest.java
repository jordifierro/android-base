package com.jordifierro.androidbase.presentation.executor;

import android.test.InstrumentationTestCase;

import org.mockito.Mockito;

import java.util.concurrent.ThreadPoolExecutor;

import rx.android.schedulers.AndroidSchedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JobExecutorTest extends InstrumentationTestCase {

    private JobExecutor jobExecutor;
    private ThreadPoolExecutor threadPoolExecutor;
    private JobExecutor.JobThreadFactory jobThreadFactory;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.jobExecutor = new JobExecutor();
        this.threadPoolExecutor = this.jobExecutor.threadPoolExecutor;
        this.jobThreadFactory = new JobExecutor.JobThreadFactory();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        this.threadPoolExecutor.shutdown();
    }

    public void testExecute() {
        ThreadPoolExecutor mockThreadPoolExecutor = Mockito.mock(ThreadPoolExecutor.class);
        this.jobExecutor.threadPoolExecutor = mockThreadPoolExecutor;

        this.jobExecutor.execute(Mockito.mock(Runnable.class));

        verify(mockThreadPoolExecutor).execute(any(Runnable.class));
    }

    public void testParams() {
        assertEquals(JobExecutor.INITIAL_POOL_SIZE, this.threadPoolExecutor.getCorePoolSize());
        assertEquals(JobExecutor.MAX_POOL_SIZE, this.threadPoolExecutor.getMaximumPoolSize());
        assertEquals(JobExecutor.KEEP_ALIVE_TIME,
                        this.threadPoolExecutor.getKeepAliveTime(JobExecutor.KEEP_ALIVE_TIME_UNIT));
    }

    public void testJobThreadFactory() {
        Runnable mockCommand = Mockito.mock(Runnable.class);
        Thread one = this.jobThreadFactory.newThread(mockCommand);
        Thread two = this.jobThreadFactory.newThread(mockCommand);

        one.run();
        two.run();

        verify(mockCommand, times(2)).run();
        assertNotSame(one, two);
        assertEquals(JobExecutor.JobThreadFactory.THREAD_NAME + "0", one.getName());
        assertEquals(JobExecutor.JobThreadFactory.THREAD_NAME + "1", two.getName());
        one.interrupt();
        two.interrupt();
    }

}
