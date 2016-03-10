package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.presentation.view.BaseView;

public class BasePresenter implements Presenter {

    BaseView view;
    private UseCase useCase;

    public BasePresenter(UseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void initWithView(BaseView view) {
        this.view = view;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.useCase.unsubscribe();
        this.view = null;
    }

    public void showLoader() {
        this.view.showLoader();
    }

    public void hideLoader() {
        this.view.hideLoader();
    }

    public void showError(String message) {
        this.view.showError(message);
    }

    public void showMessage(String message) {
        this.view.showMessage(message);
    }

    protected class BaseSubscriber<T> extends rx.Subscriber<T> {

        @Override public void onCompleted() {
            BasePresenter.this.hideLoader();
        }

        @Override public void onError(Throwable e) {
            BasePresenter.this.hideLoader();
            BasePresenter.this.showError(e.getMessage());
        }

        @Override public void onNext(T t) {
            BasePresenter.this.hideLoader();
            BasePresenter.this.showMessage(t.toString());
        }
    }
}
