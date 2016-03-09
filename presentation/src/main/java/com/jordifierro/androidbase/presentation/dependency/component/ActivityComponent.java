package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteDetailFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;
import com.jordifierro.androidbase.presentation.view.fragment.RegisterFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(NotesFragment notesFragment);
    void inject(NoteDetailFragment noteDetailFragment);

}
