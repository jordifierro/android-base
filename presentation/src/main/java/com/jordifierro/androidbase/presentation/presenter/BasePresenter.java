package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.presentation.view.BaseView;

import io.reactivex.observers.DisposableObserver;

public class BasePresenter implements Presenter {

    private BaseView view;
    private UseCase useCase0, useCase1, useCase2;

    public BasePresenter(BaseView view, UseCase useCase0) {
        this.useCase0 = useCase0;
        this.view = view;
    }

    public BasePresenter(BaseView view, UseCase useCase0, UseCase useCase1) {
        this.view = view;
        this.useCase0 = useCase0;
        this.useCase1 = useCase1;
    }

    public BasePresenter(BaseView view, UseCase useCase0, UseCase useCase1, UseCase useCase2) {
        this.view = view;
        this.useCase0 = useCase0;
        this.useCase1 = useCase1;
        this.useCase2 = useCase2;
    }


    @Override
    public void create() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        if (this.useCase0 != null) this.useCase0.unsubscribe();
        if (this.useCase1 != null) this.useCase1.unsubscribe();
        if (this.useCase2 != null) this.useCase2.unsubscribe();
        this.view = null;
    }

    public void showLoader() {
        this.view.showLoader();
    }

    public void hideLoader() {
        this.view.hideLoader();
    }

    public void handleError(Throwable error) {
        this.view.handleError(error);
    }

    public void showMessage(String message) {
        this.view.showMessage(message);
    }

    public BaseView view() {
        return view;
    }

    protected class BaseSubscriber<T> extends DisposableObserver<T> {

        @Override
        public void onComplete() {
            BasePresenter.this.hideLoader();
        }

        @Override
        public void onError(Throwable e) {
            BasePresenter.this.hideLoader();
            BasePresenter.this.handleError(e);
            e.printStackTrace();
        }

        @Override
        public void onNext(T t) {
            BasePresenter.this.hideLoader();
            //BasePresenter.this.showMessage(t.toString());
        }
    }
}
