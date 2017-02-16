package com.jordifierro.androidbase.presentation.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteDetailPresenter;
import com.jordifierro.androidbase.presentation.view.NoteDetailView;

import javax.inject.Inject;

import butterknife.Bind;

public class NoteDetailFragmentForPager extends BaseFragment implements NoteDetailView {

    @Inject
    NoteDetailPresenter noteDetailPresenter;

    @Bind(R.id.tv_title) TextView titleTV;
    @Bind(R.id.tv_content) TextView contentTV;
    private int noteId;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    public static NoteDetailFragmentForPager newInstance(int noteId) {

        Bundle args = new Bundle();
        args.putInt("argNoteId", noteId);
        NoteDetailFragmentForPager fragment = new NoteDetailFragmentForPager();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteId = getArguments().getInt("argNoteId");
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_detail;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteDetailPresenter;
    }

    @Override
    public void showNote(NoteEntity note) {
        titleTV.setText(note.getTitle());
        contentTV.setText(note.getContent());
    }

    @Override
    public int getNoteId() {
        return noteId;
    }



}
