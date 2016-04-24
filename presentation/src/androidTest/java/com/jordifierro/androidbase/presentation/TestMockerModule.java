package com.jordifierro.androidbase.presentation;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
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

    @Provides @ActivityScope
    LoginPresenter provideLoginPresenter() {
        return Mockito.mock(LoginPresenter.class);
    }

    @Provides @ActivityScope
    NoteCreatePresenter provideNoteCreatePresenter() {
        return Mockito.mock(NoteCreatePresenter.class);
    }

    @Provides @ActivityScope
    NoteDetailPresenter provideNoteDetailPresenter() {
        return Mockito.mock(NoteDetailPresenter.class);
    }

    @Provides @ActivityScope
    NotesPresenter provideNotesPresenter() {
        return Mockito.mock(NotesPresenter.class);
    }

    @Provides @ActivityScope
    RegisterPresenter provideRegisterPresenter() {
        return Mockito.mock(RegisterPresenter.class);
    }

    @Provides @ActivityScope
    SettingsPresenter provideSettingsPresenter() {
        return Mockito.mock(SettingsPresenter.class);
    }

    @Provides @ActivityScope
    NoteEditPresenter provideNoteEditPresenter() {
        return Mockito.mock(NoteEditPresenter.class);
    }

    @Provides @ActivityScope
    ResetPasswordPresenter provideResetPasswordPresenter() {
        return Mockito.mock(ResetPasswordPresenter.class);
    }

}
