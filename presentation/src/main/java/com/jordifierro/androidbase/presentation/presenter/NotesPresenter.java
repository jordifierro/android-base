package com.jordifierro.androidbase.presentation.presenter;

import android.widget.Toast;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.interactor.note.GetNotesUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.NotesView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class NotesPresenter extends BasePresenter implements Presenter {

    private GetNotesUseCase getNotesUseCase;
    private NotesView notesView;

    @Inject
    public NotesPresenter(GetNotesUseCase getNotesUseCase) {
        super(getNotesUseCase);
        this.getNotesUseCase = getNotesUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.notesView = (NotesView) view;

        this.showLoader();
        this.getNotesUseCase.execute(new NotesSubscriber());
    }

    protected class NotesSubscriber extends BaseSubscriber<List<NoteEntity>> {

        @Override public void onNext(List<NoteEntity> notes) {
            NotesPresenter.this.hideLoader();
            NotesPresenter.this.notesView.showNotes(notes);
        }
    }

    public void onNoteSelected(NoteEntity note) {
        Toast.makeText(this.notesView.context(), note.getTitle(), Toast.LENGTH_LONG).show();
    }

}
