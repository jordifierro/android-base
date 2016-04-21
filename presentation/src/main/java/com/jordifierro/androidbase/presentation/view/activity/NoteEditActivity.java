package com.jordifierro.androidbase.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.base.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.base.CleanActivity;
import com.jordifierro.androidbase.presentation.view.fragment.NoteEditFragment;

public class NoteEditActivity extends CleanActivity implements NoteEditFragment.Listener {

    public static final String PARAM_NOTE_ID = "param_note_id";

    private int noteId;
    private NoteEditFragment noteEditFragment;

    public static Intent getCallingIntent(Context context, int noteId) {
        Intent callingIntent = new Intent(context, NoteEditActivity.class);
        callingIntent.putExtra(PARAM_NOTE_ID, noteId);
        return callingIntent;
    }

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.noteId = getIntent().getIntExtra(PARAM_NOTE_ID, -1);
            if (this.noteEditFragment == null) this.noteEditFragment = new NoteEditFragment();
            addFragment(R.id.fragment_container, this.noteEditFragment);
        }
        else this.noteId = savedInstanceState.getInt(PARAM_NOTE_ID);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(PARAM_NOTE_ID, this.noteId);
        }
        super.onSaveInstanceState(outState);
    }

    public int getNoteId() {
        return this.noteId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);
        this.getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_delete) {
                    NoteEditActivity.this.callDeleteOnPresenter();
                    return true;
                }
                return false;
            }
        });
        return true;
    }

    private void callDeleteOnPresenter() {
        this.noteEditFragment.getNoteEditPresenter().deleteNoteButtonPressed();
    }
}
