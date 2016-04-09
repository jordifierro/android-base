package com.jordifierro.androidbase.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.dependency.component.FragmentInjector;
import com.jordifierro.androidbase.presentation.view.BaseView;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private FragmentInjector fragmentInjector;

    private ProgressDialog progressDialog;

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.bind(this);
        this.initializeActivity(savedInstanceState);
        this.initializeToolbar();
    }

    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((BaseApplication)getApplication()).getFragmentInjector();
        }
    }

    protected abstract void initializeActivity(Bundle savedInstanceState);

    protected void initializeToolbar(){
        if (this.useToolbar()) {
            setSupportActionBar(this.toolbar);
            if (this.useBackToolbar()) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        } else toolbar.setVisibility(View.GONE);
    }

    protected boolean useToolbar() { return true; }
    protected boolean useBackToolbar() { return true; }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    @Override
    public void showLoader() {
        if (this.progressDialog == null) this.progressDialog = new ProgressDialog(this);
        this.progressDialog.show();
    }

    @Override
    public void hideLoader() {
        if (this.progressDialog != null) this.progressDialog.dismiss();
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
