package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.VoidEntity;
import com.jordifierro.androidbase.domain.interactor.note.DeleteNoteUseCase;
import com.jordifierro.androidbase.domain.interactor.note.GetNoteUseCase;
import com.jordifierro.androidbase.domain.interactor.note.UpdateNoteUseCase;
import com.jordifierro.androidbase.presentation.view.NoteEditView;

import javax.inject.Inject;

public class NoteEditPresenter extends BasePresenter implements Presenter {

    private UpdateNoteUseCase updateNoteUseCase;
    private GetNoteUseCase getNoteUseCase;
    private DeleteNoteUseCase deleteNoteUseCase;
    private final NoteEditView noteEditView;

    @Inject
    public NoteEditPresenter(NoteEditView noteEditView, GetNoteUseCase getNoteUseCase, DeleteNoteUseCase deleteNoteUseCase, UpdateNoteUseCase updateNoteUseCase) {
        super(noteEditView, updateNoteUseCase, getNoteUseCase, deleteNoteUseCase);
        this.noteEditView = noteEditView;
        this.updateNoteUseCase = updateNoteUseCase;
        this.getNoteUseCase = getNoteUseCase;
        this.deleteNoteUseCase = deleteNoteUseCase;
    }

    public void create() {
        this.showLoader();
        this.getNoteUseCase.setParams(this.noteEditView.getNoteId());
        this.getNoteUseCase.execute(new GetNoteSubscriber());
    }

    protected class GetNoteSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.showNote(note);
        }
    }

    public void updateNote(String title, String content) {
        NoteEntity updatedNote = new NoteEntity(title, content);
        updatedNote.setId(this.noteEditView.getNoteId());

        this.noteEditView.showLoader();
        this.updateNoteUseCase.setParams(updatedNote);
        this.updateNoteUseCase.execute(new UpdateNoteSubscriber());
    }

    protected class UpdateNoteSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.close();
        }

    }

    public void deleteNoteButtonPressed(){
        this.noteEditView.showLoader();
        this.deleteNoteUseCase.setParams(this.noteEditView.getNoteId());
        this.deleteNoteUseCase.execute(new DeleteNoteSubscriber());
    }

    protected class DeleteNoteSubscriber extends BaseSubscriber<VoidEntity> {

        @Override public void onNext(VoidEntity ignore) {
            NoteEditPresenter.this.hideLoader();
            NoteEditPresenter.this.noteEditView.close();
        }

    }

}
