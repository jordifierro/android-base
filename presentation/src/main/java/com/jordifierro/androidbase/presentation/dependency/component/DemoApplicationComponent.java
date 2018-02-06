package com.jordifierro.androidbase.presentation.dependency.component;

import android.app.Application;

import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.dependency.ApplicationScope;
import com.jordifierro.androidbase.presentation.dependency.module.AndroidBaseModule;

import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {AndroidBaseModule.class})
public interface DemoApplicationComponent {

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        DemoApplicationComponent build();
    }
}
