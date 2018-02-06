package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.entity.VoidEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TimerUseCaseTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void demo_test_scheduler() throws Exception {
        TestScheduler scheduler = new TestScheduler();
        TestObserver<Long> testObserver = new TestObserver<>();
        Observable.interval(1, TimeUnit.SECONDS, scheduler).take(5).observeOn(scheduler).subscribeOn(scheduler).subscribe(testObserver);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        testObserver.assertValues(0L, 1L);
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS);
        testObserver.assertComplete();
    }

    @Test
    public void build_use_case_observable() {
        TestScheduler scheduler = new TestScheduler();
        TestObserver<VoidEntity> testObserver = new TestObserver<>();
        TimerUseCase timerUseCase = new TimerUseCase(scheduler, mockPostExecutionThread);
        timerUseCase.setParam(10).buildUseCaseObservable().subscribeWith(testObserver);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS);
        testObserver.assertValues(VoidEntity.getInstance());
        testObserver.assertComplete();
        verifyZeroInteractions(mockPostExecutionThread);
    }

    @Test
    public void build_use_case_observable_unit() {
        TestScheduler scheduler = new TestScheduler();
        TestObserver<VoidEntity> testObserver = new TestObserver<>();
        TimerUseCase timerUseCase = new TimerUseCase(scheduler, mockPostExecutionThread);
        timerUseCase.setParam(10, TimeUnit.DAYS).buildUseCaseObservable().subscribeWith(testObserver);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(10, TimeUnit.HOURS);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(14, TimeUnit.HOURS);
        testObserver.assertNoValues();
        scheduler.advanceTimeBy(9, TimeUnit.DAYS);
        testObserver.assertValues(VoidEntity.getInstance());
        testObserver.assertComplete();
        verifyZeroInteractions(mockPostExecutionThread);
    }

    @Test
    public void execute() {
        TestScheduler scheduler = new TestScheduler();

        given(mockPostExecutionThread.getScheduler()).willReturn(scheduler);

        TestDisposableObserver<VoidEntity> testObserver = new TestDisposableObserver<>();
        TimerUseCase timerUseCase = new TimerUseCase(scheduler, mockPostExecutionThread);
        timerUseCase.setParam(10).execute(testObserver);

        assertThat(testObserver.valuesCount).isEqualTo(0);
        assertThat(testObserver.completed).isFalse();
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS);
        assertThat(testObserver.valuesCount).isEqualTo(1);
        assertThat(testObserver.completed).isTrue();
        verify(mockPostExecutionThread).getScheduler();

    }

    @Test
    public void unsubscribe() {
        TestScheduler scheduler = new TestScheduler();
        TestObserver<VoidEntity> testObserver = new TestObserver<>();
        TimerUseCase timerUseCase = new TimerUseCase(scheduler, mockPostExecutionThread);
        timerUseCase.setParam(10).buildUseCaseObservable().subscribeWith(testObserver);
        testObserver.assertNoValues();
        assertEquals(timerUseCase.isUnsubscribed(), false);
        timerUseCase.unsubscribe();
        assertEquals(timerUseCase.isUnsubscribed(), true);

    }

    @Test
    public void exception_on_less_than_minimum_value() {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Illegal Params.");
        TestScheduler scheduler = new TestScheduler();
        TimerUseCase timerUseCase = new TimerUseCase(scheduler, mockPostExecutionThread);
        timerUseCase.setParam(0);
    }


    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount = 0;
        private boolean completed;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
            // no-op by default.
        }

        @Override
        public void onComplete() {
            // no-op by default.
            completed = true;
        }
    }


}