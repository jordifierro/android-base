package com.jordifierro.androidbase.presentation.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.SettingsActivity;
import com.jordifierro.androidbase.presentation.view.fragment.SettingsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
    public void testLogoutButton() {

        onView(withId(R.id.btn_logout)).perform(click());

        verify(this.settingsFragment.getSettingsPresenter()).logoutUserButtonPressed();
    }

    @Test
    public void testDeleteAccountButton() {

        onView(withId(R.id.btn_delete_account)).perform(click());

        verify(this.settingsFragment.getSettingsPresenter()).deleteAccountButtonPressed();
    }

}
