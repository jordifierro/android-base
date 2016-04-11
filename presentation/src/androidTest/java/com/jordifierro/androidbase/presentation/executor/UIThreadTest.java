package com.jordifierro.androidbase.presentation.executor;

import android.test.InstrumentationTestCase;

import rx.android.schedulers.AndroidSchedulers;

public class UIThreadTest extends InstrumentationTestCase {

    public void testExecute() {

        assertEquals(new UIThread().getScheduler(), AndroidSchedulers.mainThread());
    }
}
