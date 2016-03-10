package com.jordifierro.androidbase.presentation.view.fragment;

import android.content.Intent;
import android.widget.ListView;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NotesPresenter;
import com.jordifierro.androidbase.presentation.view.NotesView;
import com.jordifierro.androidbase.presentation.view.activity.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.NoteCreateActivity;
import com.jordifierro.androidbase.presentation.view.activity.NoteDetailActivity;
import com.jordifierro.androidbase.presentation.view.activity.SettingsActivity;
import com.jordifierro.androidbase.presentation.view.adapter.NotesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class NotesFragment extends BaseFragment implements NotesView {

    @Inject
    NotesPresenter notesPresenter;

    @Bind(R.id.listview) ListView listView;

    @Override
    protected void callInjection() {
        ((BaseActivity)getActivity()).getActivityComponent().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected BasePresenter presenter() {
        return this.notesPresenter;
    }

    @Override
    public void showNotes(List<NoteEntity> notes) {
        NotesAdapter adapter = new NotesAdapter(context());
        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onNoteItemClicked(NoteEntity note) {
                NotesFragment.this.notesPresenter.onNoteSelected(note);
            }
        });
        adapter.setNotes(notes);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_create_new_note)
    public void createNewNoteButtonPressed() {
        getActivity().startActivity(new Intent(getActivity(), NoteCreateActivity.class));
    }

    @OnClick(R.id.btn_settings)
    public void settingsButtonPressed() {
        getActivity().startActivity(new Intent(getActivity(), SettingsActivity.class));
    }

    @Override
    public void navigateToNoteDetail(int noteId) {
        getActivity().startActivity(NoteDetailActivity.getCallingIntent(getActivity(), noteId));
    }

}
