package com.jordifierro.androidbase.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.NotesFragment;

public class NotesActivity extends BaseActivity {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new NotesFragment());
        }
    }

    @Override
    protected boolean useBackToolbar() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        this.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_settings) {
                    NotesActivity.this.navigateToSettings();
                    return true;
                }
                return false;
            }
        });
        return true;
    }

    public void navigateToSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
