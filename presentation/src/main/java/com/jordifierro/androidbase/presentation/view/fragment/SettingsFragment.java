package com.jordifierro.androidbase.presentation.view.fragment;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.presenter.BasePresenter;
import com.jordifierro.androidbase.presentation.presenter.SettingsPresenter;
import com.jordifierro.androidbase.presentation.view.SettingsView;
import com.jordifierro.androidbase.presentation.view.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsView {

    @Inject SettingsPresenter settingsPresenter;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected BasePresenter presenter() {
        return this.settingsPresenter;
    }

    public SettingsPresenter getSettingsPresenter() {
        return settingsPresenter;
    }

    @OnClick(R.id.tv_logout)
    public void logoutButtonPressed() {
        this.settingsPresenter.logoutUserButtonPressed();
    }

    @OnClick(R.id.tv_delete_account)
    public void deleteAccountButtonPressed() {
        this.settingsPresenter.deleteAccountButtonPressed();
    }

    @OnClick(R.id.tv_terms)
    public void termsButtonPressed() {
        ((Listener)getActivity()).showTerms();
    }

    @OnClick(R.id.tv_privacy)
    public void privacyButtonPressed() {
        ((Listener)getActivity()).showPrivacy();
    }

    public interface Listener {
        void showTerms();
        void showPrivacy();
    }
}
