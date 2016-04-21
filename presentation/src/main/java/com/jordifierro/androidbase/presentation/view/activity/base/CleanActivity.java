package com.jordifierro.androidbase.presentation.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;
import com.jordifierro.androidbase.presentation.view.BaseView;
import com.jordifierro.androidbase.presentation.view.activity.LoginActivity;

public abstract class CleanActivity extends BaseActivity implements BaseView {

    private FragmentInjector fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }

    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((BaseApplication)getApplication()).getFragmentInjector();
        }
    }

    @Override
    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            if (((RestApiErrorException) error).getStatusCode()
                    == RestApiErrorException.UNAUTHORIZED) closeAndDisplayLogin();
            else showMessage(error.getMessage());
        }
        else Toast.makeText(context(), getResources().getString(R.string.message_error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeAndDisplayLogin() {
        Intent notesIntent = new Intent(this, LoginActivity.class);
        notesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(notesIntent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        this.finish();
    }

}
