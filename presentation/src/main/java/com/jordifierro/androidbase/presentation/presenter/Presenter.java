package com.jordifierro.androidbase.presentation.presenter;

import com.jordifierro.androidbase.presentation.view.BaseView;

public interface Presenter {

    void initWithView(BaseView view);
    void resume();
    void pause();
    void destroy();

}
