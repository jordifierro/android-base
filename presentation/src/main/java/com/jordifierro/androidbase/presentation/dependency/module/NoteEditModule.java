package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.view.NoteEditView;
import com.jordifierro.androidbase.presentation.view.fragment.NoteEditFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteEditModule {

    @Provides
    NoteEditView provideNoteCreateView(NoteEditFragment noteEditFragment) {
        return noteEditFragment;
    }

}
