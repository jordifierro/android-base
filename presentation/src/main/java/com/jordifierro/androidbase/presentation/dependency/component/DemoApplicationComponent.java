package com.jordifierro.androidbase.presentation.dependency.component;

import android.app.Application;

import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.dependency.ApplicationScope;
import com.jordifierro.androidbase.presentation.dependency.module.ApplicationModule;
import com.jordifierro.androidbase.presentation.dependency.module.DataModule;
import com.jordifierro.androidbase.presentation.dependency.module.DemoApplicationModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**

 */
@ApplicationScope
@Component(modules = {
        DemoApplicationModule.class,
        ApplicationModule.class,
        AndroidInjectionModule.class,
        DataModule.class,
        DemoActivityInjector.class,
        DemoFragmentInjector.class})
public interface DemoApplicationComponent {

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        DemoApplicationComponent build();
    }
}
