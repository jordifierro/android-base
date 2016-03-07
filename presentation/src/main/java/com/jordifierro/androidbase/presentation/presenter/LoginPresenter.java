package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.DoLoginUseCase;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.LoginView;

import javax.inject.Inject;

public class LoginPresenter implements Presenter {

    private LoginView loginView;
    private DoLoginUseCase doLoginUseCase;

    @Inject
    public LoginPresenter(DoLoginUseCase doLoginUseCase) {
        this.doLoginUseCase = doLoginUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        this.loginView = (LoginView) view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.doLoginUseCase.unsubscribe();
        this.loginView = null;
    }

    public void loginUser(String email, String password) {
        UserEntity user = new UserEntity(email);
        user.setPassword(password);

        this.showLoader();
        this.doLoginUseCase.setParams(user);
        this.doLoginUseCase.execute(new LoginSubscriber());
    }

    private void showLoader() {
        this.loginView.showLoader();
    }

    private void hideLoader() {
        this.loginView.hideLoader();
    }

    private void showError(String message) {
        this.loginView.showError(message);
    }

    private final class LoginSubscriber extends rx.Subscriber<UserEntity> {

        @Override public void onCompleted() {
            LoginPresenter.this.hideLoader();
        }

        @Override public void onError(Throwable e) {
            LoginPresenter.this.hideLoader();
            LoginPresenter.this.showError(e.getMessage());
        }

        @Override public void onNext(UserEntity user) {
            LoginPresenter.this.showError(  "User: " + user.getEmail() +
                                            " AuthToken: " + user.getAuthToken());
        }
    }
}
