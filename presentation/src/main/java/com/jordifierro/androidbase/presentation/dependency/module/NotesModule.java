package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.NotesView;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NotesModule {

    @FragmentScope
    @Provides
    NotesView provideRegisterView(NotesFragment notesFragment) {
        return notesFragment;
    }

}
