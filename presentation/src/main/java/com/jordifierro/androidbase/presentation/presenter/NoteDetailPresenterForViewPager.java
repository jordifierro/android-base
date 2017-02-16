package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.GetNoteUseCase;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

//@ActivityScope
//Comment by Tony :  how to create a child fragment activity scope
public class NoteDetailPresenterForViewPager extends BasePresenter implements Presenter {

    private GetNoteUseCase getNoteUseCase;
    NoteDetailView noteDetailView;

    @Inject
    public NoteDetailPresenterForViewPager(GetNoteUseCase getNoteUseCase) {
        super(getNoteUseCase);
        this.getNoteUseCase = getNoteUseCase;
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

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            NoteDetailPresenterForViewPager.this.noteDetailView.close();
        }

        @Override public void onNext(NoteEntity note) {
            NoteDetailPresenterForViewPager.this.hideLoader();
            NoteDetailPresenterForViewPager.this.noteDetailView.showNote(note);
        }
    }

}
