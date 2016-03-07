package com.jordifierro.androidbase.presentation;

import android.app.Application;

import com.jordifierro.androidbase.presentation.dependency.component.ApplicationComponent;
import com.jordifierro.androidbase.presentation.dependency.component.DaggerApplicationComponent;
import com.jordifierro.androidbase.presentation.dependency.module.ApplicationModule;

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjectors();
    }

    private void initializeInjectors() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
