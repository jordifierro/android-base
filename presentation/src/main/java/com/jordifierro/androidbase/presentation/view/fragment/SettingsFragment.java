package com.jordifierro.androidbase.presentation.view.fragment;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.SettingsPresenter;
import com.jordifierro.androidbase.presentation.view.SettingsView;
import com.jordifierro.androidbase.presentation.view.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsView {

    @Inject SettingsPresenter settingsPresenter;

    @Override
    protected void callInjection() {
        ((BaseActivity)getActivity()).getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected BasePresenter presenter() {
        return this.settingsPresenter;
    }

    @OnClick(R.id.btn_logout)
    public void logoutButtonPressed() {
        this.settingsPresenter.logoutUserButtonPressed();
    }

    @OnClick(R.id.btn_delete_account)
    public void deleteAccountButtonPressed() {
        this.settingsPresenter.deleteAccountButtonPressed();
    }
}
