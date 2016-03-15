package com.jordifierro.androidbase.presentation.unittest;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.dependency.component.ApplicationComponent;
import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;

import dagger.Component;

@ActivityScope
@Component(modules = TestMockModule.class, dependencies = ApplicationComponent.class)
public interface TestMockComponent extends FragmentInjector {}
