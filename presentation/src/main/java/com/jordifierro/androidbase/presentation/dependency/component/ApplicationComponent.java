package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.dependency.module.ApplicationModule;
import com.jordifierro.androidbase.presentation.dependency.module.DataModule;
import com.jordifierro.androidbase.presentation.dependency.module.UserModule;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class, UserModule.class })
public interface ApplicationComponent {

    void inject(LoginFragment loginFragment);
    void inject(NotesFragment notesFragment);

}
