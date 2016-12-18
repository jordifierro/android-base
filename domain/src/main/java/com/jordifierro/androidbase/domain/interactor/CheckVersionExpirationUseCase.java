package com.jordifierro.androidbase.domain.interactor;

import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.VersionRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckVersionExpirationUseCase extends UseCase<VersionEntity> {

    private VersionRepository versionRepository;
    private SessionRepository sessionRepository;

    @Inject
    public CheckVersionExpirationUseCase(ThreadExecutor threadExecutor,
                                         PostExecutionThread postExecutionThread,
                                         VersionRepository versionRepository,
                                         SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.versionRepository = versionRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable<VersionEntity> buildUseCaseObservable() {
        return this.versionRepository
                                .checkVersionExpiration(this.sessionRepository.getCurrentUser());
    }
}
