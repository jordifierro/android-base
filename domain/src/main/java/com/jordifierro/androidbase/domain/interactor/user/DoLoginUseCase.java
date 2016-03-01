package com.jordifierro.androidbase.domain.interactor.user;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class DoLoginUseCase extends UseCase {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private UserEntity user;
    private String password;

    @Inject
    public DoLoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                          UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(UserEntity user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.loginUser(this.user, this.password)
                .doOnNext(new Action1<UserEntity>() {
                    @Override
                    public void call(UserEntity userEntity) {
                        sessionRepository.setCurrentUser(userEntity);
                    }
                });
    }
}
