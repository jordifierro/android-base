package com.jordifierro.androidbase.presentation.view.fragment;

import android.content.Intent;
import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.LoginPresenter;
import com.jordifierro.androidbase.presentation.view.LoginView;
import com.jordifierro.androidbase.presentation.view.activity.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.NotesActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView {

    @Inject LoginPresenter loginPresenter;

    @Bind(R.id.et_email) EditText emailEditText;
    @Bind(R.id.et_password) EditText passwordEditText;

    @Override
    protected void callInjection() {
        ((BaseActivity)getActivity()).getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected BasePresenter presenter() {
        return this.loginPresenter;
    }

    @OnClick(R.id.btn_login)
    public void loginButtonPressed() {
        this.loginPresenter.loginUser(  emailEditText.getText().toString(),
                                        passwordEditText.getText().toString());
    }

    @Override
    public void navigateToNotes() {
        getActivity().startActivity(new Intent(getActivity(), NotesActivity.class));
    }
}
