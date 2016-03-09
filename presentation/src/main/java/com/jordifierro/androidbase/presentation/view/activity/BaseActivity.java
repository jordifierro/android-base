package com.jordifierro.androidbase.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.dependency.component.ActivityComponent;
import com.jordifierro.androidbase.presentation.dependency.component.DaggerActivityComponent;
import com.jordifierro.androidbase.presentation.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ActivityComponent activityComponent;

    private ProgressDialog progressDialog;

    protected abstract void initializeActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        this.initializeActivity(savedInstanceState);
    }

    private void initializeActivityComponent() {
        if (this.activityComponent == null) {
            this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((BaseApplication)getApplication()).getApplicationComponent())
                .build();
        }
    }

    public ActivityComponent getActivityComponent() {
        return this.activityComponent;
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
    public void showError(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

}
