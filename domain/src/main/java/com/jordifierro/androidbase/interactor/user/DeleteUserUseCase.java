package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.SessionRepository;
import com.jordifierro.androidbase.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action0;

public class DeleteUserUseCase extends UseCase {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    @Inject
    public DeleteUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.deleteUser(this.sessionRepository.getCurrentUser())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        sessionRepository.invalidateSession();
                    }
                });
    }
}
