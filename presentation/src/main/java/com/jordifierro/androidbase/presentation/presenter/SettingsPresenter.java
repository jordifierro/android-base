package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.interactor.user.DeleteUserUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DoLogoutUseCase;
import com.jordifierro.androidbase.presentation.view.SettingsView;

import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter implements Presenter {

    private final SettingsView settingsView;
    private DoLogoutUseCase doLogoutUseCase;
    private DeleteUserUseCase deleteUserUseCase;

    @Inject
    public SettingsPresenter(SettingsView settingsView, DoLogoutUseCase doLogoutUseCase, DeleteUserUseCase deleteUserUseCase) {
        super(settingsView, doLogoutUseCase, deleteUserUseCase);
        this.settingsView = settingsView;
        this.doLogoutUseCase = doLogoutUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    public void logoutUserButtonPressed() {
        this.doLogoutUseCase.execute(new BaseSubscriber<>());
        this.settingsView.closeAndDisplayLogin();
    }

    public void deleteAccountButtonPressed() {
        this.deleteUserUseCase.execute(new BaseSubscriber<>());
        this.settingsView.closeAndDisplayLogin();
    }

}
