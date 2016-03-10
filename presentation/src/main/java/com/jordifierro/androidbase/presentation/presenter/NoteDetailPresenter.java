package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.DeleteNoteUseCase;
import com.jordifierro.androidbase.domain.interactor.note.GetNoteUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

@ActivityScope
public class NoteDetailPresenter extends BasePresenter implements Presenter {

    private GetNoteUseCase getNoteUseCase;
    private DeleteNoteUseCase deleteNoteUseCase;
    NoteDetailView noteDetailView;

    @Inject
    public NoteDetailPresenter(GetNoteUseCase getNoteUseCase, DeleteNoteUseCase deleteNoteUseCase) {
        super(getNoteUseCase);
        this.getNoteUseCase = getNoteUseCase;
        this.deleteNoteUseCase = deleteNoteUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.noteDetailView = (NoteDetailView) view;
    }

    @Override
    public void resume() {
        this.showLoader();
        this.getNoteUseCase.setParams(this.noteDetailView.getNoteId());
        this.getNoteUseCase.execute(new NoteDetailSubscriber());
    }

    @Override
    public void destroy() {
        super.destroy();
        this.noteDetailView = null;
    }

    protected class NoteDetailSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteDetailPresenter.this.hideLoader();
            NoteDetailPresenter.this.noteDetailView.showNote(note);
        }
    }

    public void editNoteButtonPressed() {
        this.noteDetailView.navigateToEdit();
    }

    public void deleteNoteButtonPressed() {
        this.showLoader();
        this.deleteNoteUseCase.setParams(this.noteDetailView.getNoteId());
        this.deleteNoteUseCase.execute(new NoteDeleteSubscriber());
    }

    protected class NoteDeleteSubscriber extends BaseSubscriber<Void> {

        @Override
        public void onNext(Void aVoid) {
            NoteDetailPresenter.this.hideLoader();
            NoteDetailPresenter.this.noteDetailView.close();
        }
    }

}
