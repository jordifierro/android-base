package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.DoLoginUseCase;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter implements Presenter {

    private DoLoginUseCase doLoginUseCase;

    @Inject
    public LoginPresenter(DoLoginUseCase doLoginUseCase) {
        super(doLoginUseCase);
        this.doLoginUseCase = doLoginUseCase;
    }

    public void loginUser(String email, String password) {
        UserEntity user = new UserEntity(email);
        user.setPassword(password);

        this.showLoader();
        this.doLoginUseCase.setParams(user);
        this.doLoginUseCase.execute(new BaseSubscriber());
    }

}
