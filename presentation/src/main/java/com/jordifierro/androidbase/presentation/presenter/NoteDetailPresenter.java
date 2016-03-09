package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.GetNoteUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

@ActivityScope
public class NoteDetailPresenter extends BasePresenter implements Presenter {

    private GetNoteUseCase getNoteUseCase;
    private NoteDetailView noteDetailView;

    @Inject
    public NoteDetailPresenter(GetNoteUseCase getNoteUseCase) {
        super(getNoteUseCase);
        this.getNoteUseCase = getNoteUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.noteDetailView = (NoteDetailView) view;

        this.showLoader();
        this.getNoteUseCase.setParams(this.noteDetailView.getNoteId());
        this.getNoteUseCase.execute(new NoteDetailSubscriber());
    }

    protected class NoteDetailSubscriber extends BaseSubscriber<NoteEntity> {

        @Override public void onNext(NoteEntity note) {
            NoteDetailPresenter.this.hideLoader();
            NoteDetailPresenter.this.noteDetailView.showNote(note);
        }
    }

}
