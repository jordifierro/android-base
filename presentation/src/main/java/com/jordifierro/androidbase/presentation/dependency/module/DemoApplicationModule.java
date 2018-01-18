package com.jordifierro.androidbase.presentation.dependency.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class DemoApplicationModule {

    @Provides
    Context provideContext(Application application) {
        return application;
    }

}
