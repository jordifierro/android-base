package com.jordifierro.androidbase.domain.executor;

public class DefaultThreadExecutor implements ThreadExecutor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
