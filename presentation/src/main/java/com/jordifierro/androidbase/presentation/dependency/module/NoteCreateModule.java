package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.view.NoteCreateView;
import com.jordifierro.androidbase.presentation.view.fragment.NoteCreateFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteCreateModule {

    @Provides
    NoteCreateView provideNoteCreateView(NoteCreateFragment noteCreateFragment) {
        return noteCreateFragment;
    }

}
