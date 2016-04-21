package com.jordifierro.androidbase.presentation.view.activity;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.web.assertion.WebAssertion;
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

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TermsActivityTest {

    @Rule
    public final ActivityTestRule<TermsActivity> activityTestRule =
            new ActivityTestRule<>(TermsActivity.class);
    private WebViewFragment webViewFragment;

    @Before
    public void setUp() throws Exception {
        this.webViewFragment = ((WebViewFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testUrl() throws InterruptedException {
        onWebView().check(webMatches(getCurrentUrl(), equalTo(RestApi.URL_BASE + "/terms")));
        assertEquals(RestApi.URL_BASE + "/terms", this.webViewFragment.getWebView().getUrl());
    }

}
