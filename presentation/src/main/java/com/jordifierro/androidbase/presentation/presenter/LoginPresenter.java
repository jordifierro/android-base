package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.DoLoginUseCase;
import com.jordifierro.androidbase.presentation.view.LoginView;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter implements Presenter {

    private final LoginView loginView;
    private DoLoginUseCase doLoginUseCase;

    @Inject
    public LoginPresenter(LoginView loginView, DoLoginUseCase doLoginUseCase) {
        super(loginView, doLoginUseCase);
        this.loginView = loginView;
        this.doLoginUseCase = doLoginUseCase;
    }

    public void loginUser(String email, String password) {
        UserEntity user = new UserEntity(email);
        user.setPassword(password);

        this.showLoader();
        this.doLoginUseCase.setParams(user);
        this.doLoginUseCase.execute(new LoginSubscriber());
    }

    @Override
    public void create() {

    }

    protected class LoginSubscriber extends BaseSubscriber<UserEntity> {

        @Override
        public void onNext(UserEntity user) {
            LoginPresenter.this.hideLoader();
            LoginPresenter.this.loginView.viewNotes();
        }

    }

}
