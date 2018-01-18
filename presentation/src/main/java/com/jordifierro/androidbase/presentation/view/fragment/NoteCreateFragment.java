package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.TextView;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NoteCreatePresenter;
import com.jordifierro.androidbase.presentation.view.NoteCreateView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteCreateFragment extends BaseFragment implements NoteCreateView {

    @Inject
    NoteCreatePresenter noteCreatePresenter;

    @BindView(R.id.et_title) TextView titleET;
    @BindView(R.id.et_content) TextView contentET;

    @Override
    protected int layoutId() {
        return R.layout.fragment_note_create_edit;
    }

    @Override
    protected BasePresenter presenter() {
        return this.noteCreatePresenter;
    }

    public NoteCreatePresenter getNoteCreatePresenter() {
        return noteCreatePresenter;
    }

    @OnClick(R.id.btn_submit)
    public void createButtonPressed() {
        this.noteCreatePresenter.createButtonPressed(   titleET.getText().toString(),
                                                        contentET.getText().toString());
    }

}
