package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.SettingsView;
import com.jordifierro.androidbase.presentation.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    @FragmentScope
    @Provides
    SettingsView provideSettingsView(SettingsFragment settingsFragment) {
        return settingsFragment;
    }

}
