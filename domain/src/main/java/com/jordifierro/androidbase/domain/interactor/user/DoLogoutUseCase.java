package com.jordifierro.androidbase.domain.interactor.user;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoLogoutUseCase extends UseCase<Void> {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    @Inject
    public DoLogoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                           UserRepository userRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        UserEntity currentUser = this.sessionRepository.getCurrentUser();
        this.sessionRepository.invalidateSession();
        return this.userRepository.logoutUser(currentUser);
    }
}
