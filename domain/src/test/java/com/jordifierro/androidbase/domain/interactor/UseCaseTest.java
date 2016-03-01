package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.BDDMockito.given;

public class UseCaseTest {

    private TestSubscriber<Integer> testSubscriber;
    private FakeUseCase fakeUseCase;

    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.testSubscriber = new TestSubscriber<>();
        this.fakeUseCase = new FakeUseCase(new CurrentThreadExecutor(), mockPostExecutionThread);
    }

    @Test
    public void testUseCaseExecutionResult() {
        TestScheduler testScheduler = new TestScheduler();
        given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.fakeUseCase.execute(testSubscriber);
        testScheduler.triggerActions();

        this.testSubscriber.assertNoErrors();
        this.testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 3));
    }

    @Test
    public void testUseCaseUnsubscription() {

        this.fakeUseCase.execute(testSubscriber);
        this.fakeUseCase.unsubscribe();

        this.testSubscriber.assertUnsubscribed();
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

    private static class CurrentThreadExecutor implements ThreadExecutor {

        public void execute(@NotNull Runnable r) {
            r.run();
        }
    }
}
