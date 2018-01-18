package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.TextView;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteDetailPresenter;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

import butterknife.BindView;

public class NoteDetailFragment extends BaseFragment implements NoteDetailView {

    @Inject
    NoteDetailPresenter noteDetailPresenter;

    @BindView(R.id.tv_title) TextView titleTV;
    @BindView(R.id.tv_content) TextView contentTV;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_detail;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteDetailPresenter;
    }

    public NoteDetailPresenter getNoteDetailPresenter() {
        return noteDetailPresenter;
    }

    @Override
    public void showNote(NoteEntity note) {
        titleTV.setText(note.getTitle());
        contentTV.setText(note.getContent());
    }

    @Override
    public int getNoteId() {
        return ((Listener)getActivity()).getNoteId();
    }

    public interface Listener {
        int getNoteId();
    }

}
