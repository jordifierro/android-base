package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.interactor.CheckVersionExpirationUseCase;
import com.jordifierro.androidbase.domain.interactor.note.GetNotesUseCase;
import com.jordifierro.androidbase.presentation.dependency.ActivityScope;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.NotesViewPagerView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class NotesViewPagerPresenter extends BasePresenter implements Presenter {

	NotesViewPagerView notesView;
	private GetNotesUseCase getNotesUseCase;
	private CheckVersionExpirationUseCase checkVersionExpirationUseCase;

	@Inject
	public NotesViewPagerPresenter(GetNotesUseCase getNotesUseCase,
								   CheckVersionExpirationUseCase checkVersionExpirationUseCase) {
		super(getNotesUseCase, checkVersionExpirationUseCase);
		this.getNotesUseCase = getNotesUseCase;
		this.checkVersionExpirationUseCase = checkVersionExpirationUseCase;
	}

	@Override
	public void initWithView(BaseView view) {
		super.initWithView(view);
		this.notesView = (NotesViewPagerView) view;
		this.notesView.initView();
		this.checkVersionExpirationUseCase.execute(new VersionExpirationSubscriber());
	}

	@Override
	public void resume() {
		this.showLoader();
		this.getNotesUseCase.execute(new NotesSubscriber());
	}

	@Override
	public void destroy() {
		super.destroy();
		this.notesView = null;
	}

	protected class NotesSubscriber extends BaseSubscriber<List<NoteEntity>> {

		@Override
		public void onNext(List<NoteEntity> notes) {
			NotesViewPagerPresenter.this.hideLoader();
			NotesViewPagerPresenter.this.notesView.showNotes(notes);
		}
	}

	protected class VersionExpirationSubscriber extends BaseSubscriber<VersionEntity> {

		@Override
		public void onNext(VersionEntity version) {
			NotesViewPagerPresenter.this.hideLoader();
			if (version.isWarned()) NotesViewPagerPresenter.this.notesView.showExpirationWarning();
		}
	}

}
