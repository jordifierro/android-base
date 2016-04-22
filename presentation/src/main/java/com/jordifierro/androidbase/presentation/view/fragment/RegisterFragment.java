package com.jordifierro.androidbase.presentation.view.fragment;

import android.widget.EditText;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.RegisterPresenter;
import com.jordifierro.androidbase.presentation.view.RegisterView;
import com.jordifierro.androidbase.presentation.view.activity.base.BaseActivity;

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
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected BasePresenter presenter() {
        return this.registerPresenter;
    }

    public RegisterPresenter getRegisterPresenter() {
        return registerPresenter;
    }

    @OnClick(R.id.btn_register)
    public void registerButtonPressed() {
        this.registerPresenter.registerUser(emailEditText.getText().toString(),
                                            passwordEditText.getText().toString(),
                                            passwordConfirmationEditText.getText().toString());
    }

    @OnClick(R.id.tv_terms)
    public void termsPressed() {
        ((Listener)getActivity()).showTerms();
    }

    @Override
    public void viewNotes() {
        ((Listener)getActivity()).viewNotes();
    }

    public interface Listener {
        void viewNotes();
        void showTerms();
    }

}
