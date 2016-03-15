package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteCreateFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteDetailFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NoteEditFragment;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;
import com.jordifierro.androidbase.presentation.view.fragment.RegisterFragment;
import com.jordifierro.androidbase.presentation.view.fragment.SettingsFragment;

public interface FragmentInjector {

    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(NotesFragment notesFragment);
    void inject(NoteDetailFragment noteDetailFragment);
    void inject(NoteCreateFragment noteCreateFragment);
    void inject(NoteEditFragment noteEditFragment);
    void inject(SettingsFragment settingsFragment);
}
