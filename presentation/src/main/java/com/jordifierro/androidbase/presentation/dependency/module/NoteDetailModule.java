package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;
import com.jordifierro.androidbase.presentation.view.fragment.NoteDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteDetailModule {

    @FragmentScope
    @Provides
    NoteDetailView provideDetailFragmentView(NoteDetailFragment noteDetailFragment) {
        return noteDetailFragment;
    }

}
