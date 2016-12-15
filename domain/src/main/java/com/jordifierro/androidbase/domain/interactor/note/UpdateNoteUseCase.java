package com.jordifierro.androidbase.domain.interactor.note;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.domain.repository.NoteRepository;
import com.jordifierro.androidbase.domain.repository.SessionRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UpdateNoteUseCase extends UseCase<NoteEntity> {

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
    protected Observable<NoteEntity> buildUseCaseObservable() {
        return this.noteRepository.updateNote(this.sessionRepository.getCurrentUser(), this.note);
    }
}
