package com.jordifierro.androidbase.interactor.note;

import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.NoteRepository;
import com.jordifierro.androidbase.repository.SessionRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetNotesUseCase extends UseCase {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    @Inject
    public GetNotesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                           NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.noteRepository.getNotes(this.sessionRepository.getCurrentUser());
    }
}
