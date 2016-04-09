package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.interactor.user.DeleteUserUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DoLogoutUseCase;
import com.jordifierro.androidbase.presentation.view.SettingsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SettingsPresenterTest {

    @Mock DoLogoutUseCase mockDoLogoutUseCase;
    @Mock DeleteUserUseCase mockDeleteUserUseCase;
    @Mock SettingsView mockSettingsView;
    @Mock Observable mockObservable;

    private SettingsPresenter settingsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.settingsPresenter =
                new SettingsPresenter(this.mockDoLogoutUseCase, this.mockDeleteUserUseCase);
        this.settingsPresenter.initWithView(this.mockSettingsView);
    }

    @Test
    public void testDestroy() {

        this.settingsPresenter.destroy();

        verify(this.mockDoLogoutUseCase).unsubscribe();
        verify(this.mockDeleteUserUseCase).unsubscribe();
        assertNull(this.settingsPresenter.settingsView);
        assertNull(this.settingsPresenter.view);
    }

    @Test
    public void testLogoutUser() throws Exception {

        this.settingsPresenter.logoutUserButtonPressed();

        verify(this.mockDoLogoutUseCase).execute(any(BasePresenter.BaseSubscriber.class));
        verify(this.mockSettingsView).closeAndDisplayLogin();
    }

    @Test
    public void testDeleteUser() throws Exception {

        this.settingsPresenter.deleteAccountButtonPressed();

        verify(this.mockDeleteUserUseCase).execute(any(BasePresenter.BaseSubscriber.class));
        verify(this.mockSettingsView).closeAndDisplayLogin();
    }

}
