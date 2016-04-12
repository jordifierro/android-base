package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.executor.DefaultThreadExecutor;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class UseCaseTest {

    private TestSubscriber<Integer> testSubscriber;
    private FakeUseCase fakeUseCase;

    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.testSubscriber = new TestSubscriber<>();
        this.fakeUseCase = new FakeUseCase(new DefaultThreadExecutor(), mockPostExecutionThread);
    }

    @Test
    public void testUseCaseExecutionResult() {
        TestScheduler testScheduler = new TestScheduler();
        given(this.mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.fakeUseCase.execute(testSubscriber);
        testScheduler.triggerActions();

        this.testSubscriber.assertNoErrors();
        this.testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 3));
    }

    @Test
    public void testUseCaseUnsubscription() {
        TestScheduler testScheduler = new TestScheduler();
        given(this.mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.fakeUseCase.execute(testSubscriber);
        assertThat(this.fakeUseCase.isUnsubscribed(), is(false));

        this.fakeUseCase.unsubscribe();
        assertThat(this.fakeUseCase.isUnsubscribed(), is(true));
    }

    private static class FakeUseCase extends UseCase {

        protected FakeUseCase(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override protected Observable buildUseCaseObservable() {
            return Observable.just(1, 2, 3);
        }
    }

}
