package com.jordifierro.androidbase.presentation.view.activity;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.presentation.view.fragment.WebViewFragment;

public class PrivacyActivity extends WebViewActivity {

    private static final String TERMS_URL = RestApi.URL_BASE + "/privacy";

    @Override
    public String getUrl() {
        return TERMS_URL;
    }

}
