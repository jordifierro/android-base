package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.FragmentScope;
import com.jordifierro.androidbase.presentation.view.LoginView;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @FragmentScope
    @Provides
    LoginView provideLoginView(LoginFragment loginFragment) {
        return loginFragment;
    }

}
