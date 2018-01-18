package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.RegisterView;
import com.jordifierro.androidbase.presentation.view.fragment.RegisterFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {

    @FragmentScope
    @Provides
    RegisterView provideRegisterView(RegisterFragment registerFragment) {
        return registerFragment;
    }

}
