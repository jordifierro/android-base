package com.jordifierro.androidbase.presentation.dependency.component;

import com.jordifierro.androidbase.presentation.dependency.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends FragmentInjector {}
