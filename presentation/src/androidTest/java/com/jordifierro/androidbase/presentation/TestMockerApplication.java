package com.jordifierro.androidbase.presentation;


public class TestMockerApplication extends BaseApplication {


    public void initializeInjector() {
        DaggerTestMockerComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

}
