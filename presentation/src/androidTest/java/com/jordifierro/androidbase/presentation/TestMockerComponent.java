package com.jordifierro.androidbase.presentation;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.dependency.component.ApplicationComponent;
import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;

import dagger.Component;

@ActivityScope
@Component(modules = TestMockerModule.class, dependencies = ApplicationComponent.class)
public interface TestMockerComponent extends FragmentInjector {}
