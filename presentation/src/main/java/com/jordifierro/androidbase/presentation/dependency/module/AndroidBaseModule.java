package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.presentation.dependency.component.DemoActivityInjector;
import com.jordifierro.androidbase.presentation.dependency.component.DemoFragmentInjector;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Module(includes = {
        DemoApplicationModule.class,
        ApplicationModule.class,
        AndroidInjectionModule.class,
        DataModule.class,
        DemoActivityInjector.class,
        DemoFragmentInjector.class
})
public class AndroidBaseModule {


}
