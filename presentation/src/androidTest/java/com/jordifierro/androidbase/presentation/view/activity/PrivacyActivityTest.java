package com.jordifierro.androidbase.presentation.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.WebViewFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PrivacyActivityTest {

    @Rule
    public final ActivityTestRule<PrivacyActivity> activityTestRule =
            new ActivityTestRule<>(PrivacyActivity.class);
    private WebViewFragment webViewFragment;

    @Before
    public void setUp() throws Exception {
        this.webViewFragment = ((WebViewFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testUrl() throws InterruptedException {
        onWebView().check(webMatches(getCurrentUrl(), equalTo(RestApi.URL_BASE + "/privacy")));
        assertEquals(RestApi.URL_BASE + "/privacy", this.webViewFragment.getWebView().getUrl());
    }

}
