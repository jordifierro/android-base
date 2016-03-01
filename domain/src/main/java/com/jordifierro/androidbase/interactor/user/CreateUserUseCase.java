package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.entity.UserEntity;
import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.SessionRepository;
import com.jordifierro.androidbase.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class CreateUserUseCase extends UseCase {

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
