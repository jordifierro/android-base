package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.SessionRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class DoLoginUseCase extends UseCase {

    private SessionRepository sessionRepository;

    private String email;
    private String password;

    @Inject
    public DoLoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                          SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.sessionRepository = sessionRepository;
    }

    public void execute(Subscriber useCaseSubscriber,
                        String email, String password) {
        this.email = email;
        this.password = password;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.sessionRepository.loginUser(this.email, this.password);
    }
}
