package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.interactor.CheckVersionExpirationUseCase;
import com.jordifierro.androidbase.domain.interactor.note.GetNotesUseCase;
import com.jordifierro.androidbase.presentation.view.NotesView;

import java.util.List;

import javax.inject.Inject;

public class NotesPresenter extends BasePresenter implements Presenter {

    private final NotesView notesView;
    private GetNotesUseCase getNotesUseCase;
    private CheckVersionExpirationUseCase checkVersionExpirationUseCase;

    @Inject
    public NotesPresenter(NotesView notesView, CheckVersionExpirationUseCase checkVersionExpirationUseCase, GetNotesUseCase getNotesUseCase) {
        super(notesView, getNotesUseCase, checkVersionExpirationUseCase);
        this.notesView = notesView;
        this.getNotesUseCase = getNotesUseCase;
        this.checkVersionExpirationUseCase = checkVersionExpirationUseCase;
    }

    @Override
    public void create() {
        this.checkVersionExpirationUseCase.execute(new VersionExpirationSubscriber());
    }

    @Override
    public void resume() {
        this.showLoader();
        this.getNotesUseCase.execute(new NotesSubscriber());
    }

    protected class NotesSubscriber extends BaseSubscriber<List<NoteEntity>> {

        @Override
        public void onNext(List<NoteEntity> notes) {
            NotesPresenter.this.hideLoader();
            NotesPresenter.this.notesView.showNotes(notes);
        }
    }

    protected class VersionExpirationSubscriber extends BaseSubscriber<VersionEntity> {

        @Override
        public void onNext(VersionEntity version) {
            NotesPresenter.this.hideLoader();
            if (version.isWarned()) NotesPresenter.this.notesView.showExpirationWarning();
        }
    }

}
