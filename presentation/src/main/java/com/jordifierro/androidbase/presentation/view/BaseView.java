package com.jordifierro.androidbase.presentation.view;

import android.content.Context;

public interface BaseView {

    Context context();

    void showLoader();
    void hideLoader();
    void showError(String message);
}
