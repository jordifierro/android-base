package com.jordifierro.androidbase.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteEditPresenter;
import com.jordifierro.androidbase.presentation.view.NoteEditView;
import com.jordifierro.androidbase.presentation.view.activity.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.NoteEditActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class NoteEditFragment extends BaseFragment implements NoteEditView {

    @Inject
    NoteEditPresenter noteEditPresenter;

    @Bind(R.id.et_title) EditText titleET;
    @Bind(R.id.et_content) EditText contentET;
    @Bind(R.id.btn_submit) Button submitButton;

    @Override
    protected void callInjection() {
        ((BaseActivity)getActivity()).getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_create_edit;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteEditPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        submitButton.setText(getActivity().getResources().getText(R.string.button_save));
        return view;
    }

    @Override
    public void showNote(NoteEntity note) {
        titleET.setText(note.getTitle());
        contentET.setText(note.getContent());
    }

    @OnClick(R.id.btn_submit)
    public void updateNoteButtonPressed() {
        this.noteEditPresenter.updateNote(  titleET.getText().toString(),
                                            contentET.getText().toString());
    }

    @Override
    public int getNoteId() {
        return ((NoteEditActivity)getActivity()).getNoteId();
    }

    public NoteEditPresenter getNoteEditPresenter() {
        return noteEditPresenter;
    }
}
