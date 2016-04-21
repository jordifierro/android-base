package com.jordifierro.androidbase.presentation.view.activity;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.presentation.view.activity.base.WebViewActivity;

public class TermsActivity extends WebViewActivity {

    private static final String TERMS_URL = RestApi.URL_BASE + "/terms";

    @Override
    public String getUrl() {
        return TERMS_URL;
    }

}
