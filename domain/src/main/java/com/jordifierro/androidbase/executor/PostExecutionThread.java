package com.jordifierro.androidbase.executor;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
