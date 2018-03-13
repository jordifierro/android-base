package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.GetNoteUseCase;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

public class NoteDetailPresenter extends BasePresenter implements Presenter {

    private final NoteDetailView noteDetailView;
    private GetNoteUseCase getNoteUseCase;

    @Inject
    public NoteDetailPresenter(NoteDetailView noteDetailView, GetNoteUseCase getNoteUseCase) {
        super(noteDetailView, getNoteUseCase);
        this.noteDetailView = noteDetailView;
        this.getNoteUseCase = getNoteUseCase;
    }

    @Override
    public void create() {

    }

    @Override
    public void resume() {
        this.showLoader();
        this.getNoteUseCase.setParams(this.noteDetailView.getNoteId());
        this.getNoteUseCase.execute(new NoteDetailSubscriber());
    }

    protected class NoteDetailSubscriber extends BaseSubscriber<NoteEntity> {

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            NoteDetailPresenter.this.noteDetailView.close();
        }

        @Override
        public void onNext(NoteEntity note) {
            NoteDetailPresenter.this.hideLoader();
            NoteDetailPresenter.this.noteDetailView.showNote(note);
        }
    }

}
