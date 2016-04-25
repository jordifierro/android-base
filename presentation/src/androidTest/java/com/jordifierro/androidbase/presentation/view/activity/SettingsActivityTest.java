package com.jordifierro.androidbase.presentation.view.activity;

import android.content.pm.PackageManager;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.SettingsActivity;
import com.jordifierro.androidbase.presentation.view.fragment.SettingsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SettingsActivityTest {

    @Rule
    public final ActivityTestRule<SettingsActivity> activityTestRule = new ActivityTestRule<>(
            SettingsActivity.class);
    private SettingsFragment settingsFragment;

    @Before
    public void setUp() throws Exception {
        this.settingsFragment = ((SettingsFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testViewElements() throws PackageManager.NameNotFoundException {
        onView(allOf(isAssignableFrom(TextView.class),withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_activity_settings)));
        onView(withId(R.id.tv_logout)).check(matches(withText(R.string.textview_logout)));
        onView(withId(R.id.tv_delete_account))
                .check(matches(withText(R.string.textview_delete_account)));
        onView(withId(R.id.tv_terms)).check(matches(withText(R.string.title_activity_terms)));
        onView(withId(R.id.tv_privacy)).check(matches(withText(R.string.title_activity_privacy)));
    }

    @Test
    public void testLogoutClicked() {

        onView(withId(R.id.tv_logout)).perform(click());

        verify(this.settingsFragment.getSettingsPresenter()).logoutUserButtonPressed();
    }

    @Test
    public void testDeleteAccountClicked() {

        onView(withId(R.id.tv_delete_account)).perform(click());

        verify(this.settingsFragment.getSettingsPresenter()).deleteAccountButtonPressed();
    }

    @Test
    public void testTermsClicked() {
        Intents.init();

        onView(withId(R.id.tv_terms)).perform(click());

        intended(hasComponent(TermsActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testPrivacyClicked() {
        Intents.init();

        onView(withId(R.id.tv_privacy)).perform(click());

        intended(hasComponent(PrivacyActivity.class.getName()));
        Intents.release();
    }

}
