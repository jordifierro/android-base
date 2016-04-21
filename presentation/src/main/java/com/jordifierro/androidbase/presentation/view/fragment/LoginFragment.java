package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.LoginPresenter;
import com.jordifierro.androidbase.presentation.view.LoginView;
import com.jordifierro.androidbase.presentation.view.activity.base.BaseActivity;
import com.jordifierro.androidbase.presentation.view.activity.base.CleanActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @Bind(R.id.et_email) EditText emailEditText;
    @Bind(R.id.et_password) EditText passwordEditText;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

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
        this.loginPresenter.loginUser(  emailEditText.getText().toString(),
                                        passwordEditText.getText().toString());
    }

    @Override
    public void viewNotes() {
        ((Listener)getActivity()).viewNotes();
    }

    @OnClick(R.id.btn_register)
    public void registerButtonPressed() {
        ((Listener)getActivity()).displayRegister();
    }

    public interface Listener {
        void viewNotes();
        void displayRegister();
    }
}
