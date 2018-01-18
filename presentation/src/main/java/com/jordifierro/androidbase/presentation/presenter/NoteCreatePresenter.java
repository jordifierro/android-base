package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.CreateNoteUseCase;
import com.jordifierro.androidbase.presentation.view.NoteCreateView;

import javax.inject.Inject;

public class NoteCreatePresenter extends BasePresenter implements Presenter {

    private final NoteCreateView noteCreateView;
    private CreateNoteUseCase createNoteUseCase;

    @Inject
    public NoteCreatePresenter(NoteCreateView noteCreateView, CreateNoteUseCase createNoteUseCase) {
        super(noteCreateView, createNoteUseCase);
        this.noteCreateView = noteCreateView;
        this.createNoteUseCase = createNoteUseCase;
    }

    public void createButtonPressed(String title, String content) {
        NoteEntity note = new NoteEntity(title, content);

        this.noteCreateView.showLoader();
        this.createNoteUseCase.setParams(note);
        this.createNoteUseCase.execute(new NoteCreateSubscriber());
    }

    @Override
    public void create() {

    }

    protected class NoteCreateSubscriber extends BaseSubscriber<NoteEntity> {

        @Override
        public void onNext(NoteEntity note) {
            NoteCreatePresenter.this.hideLoader();
            NoteCreatePresenter.this.noteCreateView.close();
        }
    }

}
