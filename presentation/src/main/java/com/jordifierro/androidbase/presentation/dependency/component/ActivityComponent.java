package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(LoginFragment loginFragment);
    void inject(NotesFragment notesFragment);

}
