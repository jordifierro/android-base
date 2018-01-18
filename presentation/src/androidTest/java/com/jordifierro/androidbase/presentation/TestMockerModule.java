package com.jordifierro.androidbase.presentation;

import com.jordifierro.androidbase.presentation.presenter.LoginPresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteCreatePresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteDetailPresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteEditPresenter;
import com.jordifierro.androidbase.presentation.presenter.NotesPresenter;
import com.jordifierro.androidbase.presentation.presenter.RegisterPresenter;
import com.jordifierro.androidbase.presentation.presenter.ResetPasswordPresenter;
import com.jordifierro.androidbase.presentation.presenter.SettingsPresenter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestMockerModule {

    @Provides
    LoginPresenter provideLoginPresenter() {
        return Mockito.mock(LoginPresenter.class);
    }

    @Provides
    NoteCreatePresenter provideNoteCreatePresenter() {
        return Mockito.mock(NoteCreatePresenter.class);
    }

    @Provides
    NoteDetailPresenter provideNoteDetailPresenter() {
        return Mockito.mock(NoteDetailPresenter.class);
    }

    @Provides
    NotesPresenter provideNotesPresenter() {
        return Mockito.mock(NotesPresenter.class);
    }

    @Provides
    RegisterPresenter provideRegisterPresenter() {
        return Mockito.mock(RegisterPresenter.class);
    }

    @Provides
    SettingsPresenter provideSettingsPresenter() {
        return Mockito.mock(SettingsPresenter.class);
    }

    @Provides
    NoteEditPresenter provideNoteEditPresenter() {
        return Mockito.mock(NoteEditPresenter.class);
    }

    @Provides
    ResetPasswordPresenter provideResetPasswordPresenter() {
        return Mockito.mock(ResetPasswordPresenter.class);
    }

}
