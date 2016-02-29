package com.jordifierro.androidbase.interactor.user;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class CreateUserUseCase extends UseCase {

    private UserRepository userRepository;

    private String email;
    private String password;
    private String confirmationPassword;

    @Inject
    public CreateUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    public void execute(Subscriber useCaseSubscriber,
                        String email, String password, String confirmationPassword) {
        this.email = email;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.createUser(this.email, this.password, this.confirmationPassword);
    }
}
