package com.jordifierro.androidbase.presentation.dependency.module;

import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.user.CreateUserUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DeleteUserUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DoLoginUseCase;
import com.jordifierro.androidbase.domain.interactor.user.DoLogoutUseCase;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    DoLoginUseCase provideDoLoginUseCase(ThreadExecutor threadExecutor,
                                         PostExecutionThread postExecutionThread,
                                         UserRepository userRepository,
                                         SessionRepository sessionRepository) {
        return new DoLoginUseCase(  threadExecutor, postExecutionThread,
                                    userRepository, sessionRepository);
    }

    @Provides
    DoLogoutUseCase provideDoLogoutUseCase(ThreadExecutor threadExecutor,
                                          PostExecutionThread postExecutionThread,
                                          UserRepository userRepository,
                                          SessionRepository sessionRepository) {
        return new DoLogoutUseCase(  threadExecutor, postExecutionThread,
                userRepository, sessionRepository);
    }

    @Provides
    CreateUserUseCase provideCreateUserUseCase(ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread,
                                            UserRepository userRepository,
                                            SessionRepository sessionRepository) {
        return new CreateUserUseCase(  threadExecutor, postExecutionThread,
                userRepository, sessionRepository);
    }

    @Provides
    DeleteUserUseCase provideDeleteUserUseCase(ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread,
                                            UserRepository userRepository,
                                            SessionRepository sessionRepository) {
        return new DeleteUserUseCase(  threadExecutor, postExecutionThread,
                userRepository, sessionRepository);
    }

}
