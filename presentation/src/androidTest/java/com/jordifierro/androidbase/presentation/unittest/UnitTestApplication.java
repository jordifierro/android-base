package com.jordifierro.androidbase.presentation.unittest;

import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;

public class UnitTestApplication extends BaseApplication {

    @Override
    public FragmentInjector getFragmentInjector() {
        return DaggerTestMockComponent.builder()
                .applicationComponent(this.applicationComponent)
                .testMockModule(new TestMockModule()).build();
    }
}
