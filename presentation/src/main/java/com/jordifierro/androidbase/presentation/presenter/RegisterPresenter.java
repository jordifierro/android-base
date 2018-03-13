package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.interactor.user.CreateUserUseCase;
import com.jordifierro.androidbase.presentation.view.RegisterView;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter implements Presenter {

    private final RegisterView registerView;
    private CreateUserUseCase createUserUseCase;

    @Inject
    public RegisterPresenter(RegisterView registerView, CreateUserUseCase createUserUseCase) {
        super(registerView, createUserUseCase);
        this.registerView = registerView;
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void create() {

    }


    public void registerUser(String email, String password, String passwordConfirmation) {
        UserEntity user = new UserEntity(email);
        user.setPassword(password);
        user.setPasswordConfirmation(passwordConfirmation);

        this.showLoader();
        this.createUserUseCase.setParams(user);
        this.createUserUseCase.execute(new RegisterSubscriber());
    }

    protected class RegisterSubscriber extends BaseSubscriber<UserEntity> {

        @Override
        public void onNext(UserEntity user) {
            RegisterPresenter.this.hideLoader();
            RegisterPresenter.this.registerView.viewNotes();
        }

    }

}
