package com.jordifierro.androidbase.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.NoteDetailFragment;

public class NoteDetailActivity extends BaseActivity {

    private static final String PARAM_NOTE_ID = "param_note_id";

    private int noteId;

    public static Intent getCallingIntent(Context context, int noteId) {
        Intent callingIntent = new Intent(context, NoteDetailActivity.class);
        callingIntent.putExtra(PARAM_NOTE_ID, noteId);
        return callingIntent;
    }

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.noteId = getIntent().getIntExtra(PARAM_NOTE_ID, -1);
            addFragment(R.id.fragment_container, new NoteDetailFragment());
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
}
