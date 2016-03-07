package com.jordifierro.androidbase.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jordifierro.androidbase.presentation.BaseApplication;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.LoginPresenter;
import com.jordifierro.androidbase.presentation.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView {

    @Inject LoginPresenter loginPresenter;

    @Bind(R.id.et_email) EditText emailEditText;
    @Bind(R.id.et_password) EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loginPresenter.initWithView(this);
    }

    @OnClick(R.id.btn_login)
    public void loginButtonPressed() {
        this.loginPresenter.loginUser(  emailEditText.getText().toString(),
                                        passwordEditText.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loginPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.loginPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.loginPresenter.destroy();
    }
}
