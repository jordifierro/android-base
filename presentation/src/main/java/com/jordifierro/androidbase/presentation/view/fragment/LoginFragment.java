package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.LoginPresenter;
import com.jordifierro.androidbase.presentation.view.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.et_email)
    EditText emailEditText;
    @BindView(R.id.et_password)
    EditText passwordEditText;

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public BasePresenter presenter() {
        return this.loginPresenter;
    }

    public LoginPresenter getLoginPresenter() {
        return loginPresenter;
    }

    @OnClick(R.id.btn_login)
    public void loginButtonPressed() {
        this.loginPresenter.loginUser(emailEditText.getText().toString(),
                passwordEditText.getText().toString());
    }

    @Override
    public void viewNotes() {
        ((Listener) getActivity()).viewNotes();
    }

    @OnClick(R.id.btn_register)
    public void registerButtonPressed() {
        ((Listener) getActivity()).displayRegister();
    }

    @OnClick(R.id.tv_forgot_password)
    public void forgotPasswordPressed() {
        ((Listener) getActivity()).forgotPassword();
    }

    public interface Listener {
        void viewNotes();

        void displayRegister();

        void forgotPassword();
    }
}
