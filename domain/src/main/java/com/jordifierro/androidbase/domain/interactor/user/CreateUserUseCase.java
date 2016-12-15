package com.jordifierro.androidbase.domain.interactor.user;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CreateUserUseCase extends UseCase<UserEntity> {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private UserEntity user;

    @Inject
    public CreateUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(UserEntity user) {
        this.user = user;
    }

    @Override
    protected Observable<UserEntity> buildUseCaseObservable() {
        return this.userRepository.createUser(this.user)
                .doOnNext(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        sessionRepository.setCurrentUser(userEntity);
                    }
                });
    }
}
