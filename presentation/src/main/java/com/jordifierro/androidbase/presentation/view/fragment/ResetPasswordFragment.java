package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.ResetPasswordPresenter;
import com.jordifierro.androidbase.presentation.view.ResetPasswordView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPasswordFragment extends BaseFragment implements ResetPasswordView {

    @Inject
    ResetPasswordPresenter resetPasswordPresenter;

    @BindView(R.id.et_email) EditText emailEditText;
    @BindView(R.id.et_password) EditText passwordEditText;
    @BindView(R.id.et_password_confirmation) EditText passwordConfirmationEditText;

    @Override
    protected int layoutId() {
        return R.layout.fragment_reset_password;
    }

    @Override
    protected BasePresenter presenter() {
        return this.resetPasswordPresenter;
    }

    public ResetPasswordPresenter getResetPasswordPresenter() {
        return this.resetPasswordPresenter;
    }

    @OnClick(R.id.btn_resetpassword)
    public void resetPasswordButtonPressed() {
        this.resetPasswordPresenter.resetPassword(emailEditText.getText().toString(),
                                            passwordEditText.getText().toString(),
                                            passwordConfirmationEditText.getText().toString());
    }

}
