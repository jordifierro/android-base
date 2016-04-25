package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.interactor.user.DeleteUserUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DoLogoutUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.SettingsView;

import javax.inject.Inject;

@ActivityScope
public class SettingsPresenter extends BasePresenter implements Presenter {

    private DoLogoutUseCase doLogoutUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    SettingsView settingsView;

    @Inject
    public SettingsPresenter(DoLogoutUseCase doLogoutUseCase, DeleteUserUseCase deleteUserUseCase) {
        super(doLogoutUseCase, deleteUserUseCase);
        this.doLogoutUseCase = doLogoutUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.settingsView = (SettingsView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.settingsView = null;
    }

    public void logoutUserButtonPressed() {
        this.doLogoutUseCase.execute(new BaseSubscriber());
        this.settingsView.closeAndDisplayLogin();
    }

    public void deleteAccountButtonPressed() {
        this.deleteUserUseCase.execute(new BaseSubscriber());
        this.settingsView.closeAndDisplayLogin();
    }

}
