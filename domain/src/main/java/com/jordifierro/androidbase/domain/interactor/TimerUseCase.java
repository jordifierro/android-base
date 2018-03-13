package com.jordifierro.androidbase.domain.interactor;


import com.jordifierro.androidbase.domain.entity.VoidEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

public class TimerUseCase {

    public static final int DEFAULT_DELAY = 1;
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private final Scheduler scheduler;
    private final PostExecutionThread postExecutionThread;
    protected Disposable disposable = Disposables.empty();
    private int delay = DEFAULT_DELAY;
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;

    @Inject
    public TimerUseCase(Scheduler scheduler, PostExecutionThread postExecutionThread) {
        this.scheduler = scheduler;
        this.postExecutionThread = postExecutionThread;
    }

    public Observable<VoidEntity> buildUseCaseObservable() {
        return Observable.timer(delay, timeUnit, scheduler).map(aLong -> VoidEntity.getInstance());
    }

    public void execute(DisposableObserver<VoidEntity> useCaseDisposable) {
        this.disposable = this.buildUseCaseObservable()
                .subscribeOn(scheduler)
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(useCaseDisposable);
    }

    public void unsubscribe() {
        if (!isUnsubscribed()) {
            this.disposable.dispose();
        }
    }

    public boolean isUnsubscribed() {
        return this.disposable.isDisposed();
    }

    public TimerUseCase setParam(int delay) {
        return setParam(delay, TimeUnit.SECONDS);
    }

    public TimerUseCase setParam(int delay, TimeUnit timeUnit) {
        checkDelayParams(delay);
        this.delay = delay;
        this.timeUnit = timeUnit;
        return this;
    }


    private void checkDelayParams(int delay) {
        if (isInvalidParam(delay)) {
            throw new RuntimeException("Illegal Params.");
        }
    }

    private boolean isInvalidParam(int delay) {
        return delay <= 0;
    }


}
