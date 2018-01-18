package com.jordifierro.androidbase.presentation;

import com.jordifierro.androidbase.presentation.dependency.ApplicationScope;
import com.jordifierro.androidbase.presentation.dependency.component.DemoApplicationComponent;
import com.jordifierro.androidbase.presentation.dependency.module.AndroidBaseModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {AndroidBaseModule.class, TestMockerModule.class})
public interface TestMockerComponent extends DemoApplicationComponent {

    @Component.Builder
    interface Builder extends DemoApplicationComponent.Builder {
    }
}
