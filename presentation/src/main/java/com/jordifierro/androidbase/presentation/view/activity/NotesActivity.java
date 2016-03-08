package com.jordifierro.androidbase.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;

public class NotesActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        addFragment(R.id.fragment_container, new NotesFragment());
    }
}
