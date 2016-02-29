package com.jordifierro.androidbase.interactor.note;

import com.jordifierro.androidbase.entity.NoteEntity;
import com.jordifierro.androidbase.executor.PostExecutionThread;
import com.jordifierro.androidbase.executor.ThreadExecutor;
import com.jordifierro.androidbase.interactor.UseCase;
import com.jordifierro.androidbase.repository.NoteRepository;
import com.jordifierro.androidbase.repository.SessionRepository;

import javax.inject.Inject;

import rx.Observable;

public class UpdateNoteUseCase extends UseCase {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    private NoteEntity note;

    @Inject
    public UpdateNoteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(NoteEntity note) {
        this.note = note;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.noteRepository.updateNote(this.sessionRepository.getCurrentUser(), this.note);
    }
}
