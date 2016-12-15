package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    protected Disposable disposable = Disposables.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public <S extends Observer<T> & Disposable> void execute(S useCaseDisposable) {
        this.disposable = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(useCaseDisposable);
    }

    public void unsubscribe() {
        if (!this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    public boolean isUnsubscribed() {
        return this.disposable.isDisposed();
    }

}
