package com.jordifierro.androidbase.presentation.view.fragment;

import android.content.Intent;
import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.RegisterPresenter;
import com.jordifierro.androidbase.presentation.view.RegisterView;
import com.jordifierro.androidbase.presentation.view.activity.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.LoginActivity;
import com.jordifierro.androidbase.presentation.view.activity.NotesActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterView {

    @Inject
    RegisterPresenter registerPresenter;

    @Bind(R.id.et_email) EditText emailEditText;
    @Bind(R.id.et_password) EditText passwordEditText;
    @Bind(R.id.et_password_confirmation) EditText passwordConfirmationEditText;

    @Override
    protected void callInjection() {
        ((BaseActivity)getActivity()).getActivityComponent().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected BasePresenter presenter() {
        return this.registerPresenter;
    }

    @OnClick(R.id.btn_register)
    public void registerButtonPressed() {
        this.registerPresenter.registerUser(emailEditText.getText().toString(),
                                            passwordEditText.getText().toString(),
                                            passwordConfirmationEditText.getText().toString());
    }

    @OnClick(R.id.btn_goto_login)
    public void gotoLoginButtonPressed() {
        this.registerPresenter.loginButtonClick();
    }

    @Override
    public void navigateToNotes() {
        getActivity().startActivity(new Intent(getActivity(), NotesActivity.class));
    }

    @Override
    public void navigateToLogin() {
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
