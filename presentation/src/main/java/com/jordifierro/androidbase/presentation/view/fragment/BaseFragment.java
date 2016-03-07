package com.jordifierro.androidbase.presentation.view.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.jordifierro.androidbase.presentation.view.BaseView;

public class BaseFragment extends Fragment implements BaseView {

    private ProgressDialog progressDialog;

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void showLoader() {
        if (this.progressDialog == null) this.progressDialog = new ProgressDialog(getActivity());
        this.progressDialog.show();
    }

    @Override
    public void hideLoader() {
        if (this.progressDialog != null) this.progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show();
    }

}
