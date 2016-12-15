package com.jordifierro.androidbase.domain.interactor.note;

import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.interactor.UseCase;
import com.jordifierro.androidbase.domain.repository.NoteRepository;
import com.jordifierro.androidbase.domain.repository.SessionRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeleteNoteUseCase extends UseCase<Void> {

    private NoteRepository noteRepository;
    private SessionRepository sessionRepository;

    private int noteId;

    @Inject
    public DeleteNoteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             NoteRepository noteRepository, SessionRepository sessionRepository) {
        super(threadExecutor, postExecutionThread);
        this.noteRepository = noteRepository;
        this.sessionRepository = sessionRepository;
    }

    public void setParams(int noteId) {
        this.noteId = noteId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.noteRepository.deleteNote(this.sessionRepository.getCurrentUser(), this.noteId);
    }
}
