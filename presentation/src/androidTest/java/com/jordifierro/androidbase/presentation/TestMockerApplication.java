package com.jordifierro.androidbase.presentation;

import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;

public class TestMockerApplication extends BaseApplication {

    @Override
    public FragmentInjector getFragmentInjector() {
        return DaggerTestMockerComponent.builder()
                .applicationComponent(this.applicationComponent)
                .testMockerModule(new TestMockerModule()).build();
    }
}
