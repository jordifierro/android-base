package com.jordifierro.androidbase.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
