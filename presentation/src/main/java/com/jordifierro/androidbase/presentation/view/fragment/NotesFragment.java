package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.ListView;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.NotesPresenter;
import com.jordifierro.androidbase.presentation.view.NotesView;
import com.jordifierro.androidbase.presentation.view.activity.base.BaseActivity;
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
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected BasePresenter presenter() {
        return this.notesPresenter;
    }

    public NotesPresenter getNotesPresenter() {
        return notesPresenter;
    }

    @Override
    public void showNotes(List<NoteEntity> notes) {
        NotesAdapter adapter = new NotesAdapter(getActivity());
        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onNoteItemClicked(NoteEntity note) {
                NotesFragment.this.showNote(note.getId());
            }
        });
        adapter.setNotes(notes);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.btn_create_new_note)
    public void createNewNoteButtonPressed() {
        ((Listener)getActivity()).diplayNoteCreator();
    }

    @Override
    public void showNote(int noteId) {
        ((Listener)getActivity()).showNote(noteId);
    }

    @Override
    public void showExpirationDate(String date) {
        showMessage(getResources().getString(R.string.message_expiration) + " " + date + ".\n" +
                    getResources().getString(R.string.message_update) + ".");
    }

    public interface Listener {
        void diplayNoteCreator();
        void showNote(int noteId);
    }

}
