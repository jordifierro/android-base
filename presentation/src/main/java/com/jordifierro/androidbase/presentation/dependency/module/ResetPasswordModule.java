package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.ResetPasswordView;
import com.jordifierro.androidbase.presentation.view.fragment.ResetPasswordFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ResetPasswordModule {

    @FragmentScope
    @Provides
    ResetPasswordView provideResetPasswordView(ResetPasswordFragment resetPasswordFragment) {
        return resetPasswordFragment;
    }

}
