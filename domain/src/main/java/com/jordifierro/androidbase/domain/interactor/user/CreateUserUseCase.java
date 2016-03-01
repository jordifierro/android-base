package com.jordifierro.androidbase.domain.interactor.user;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class CreateUserUseCase extends com.jordifierro.androidbase.domain.interactor.UseCase {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private UserEntity user;
    private String password;
    private String confirmationPassword;

    @Inject
    public CreateUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(UserEntity user, String password, String confirmationPassword) {
        this.user = user;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.createUser(this.user, this.password, this.confirmationPassword)
                .doOnNext(new Action1<UserEntity>() {
                    @Override
                    public void call(UserEntity userEntity) {
                        sessionRepository.setCurrentUser(userEntity);
                    }
                });
    }
}
