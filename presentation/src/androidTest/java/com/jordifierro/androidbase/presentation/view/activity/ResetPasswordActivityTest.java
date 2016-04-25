package com.jordifierro.androidbase.presentation.view.activity;

import android.content.pm.PackageManager;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.RegisterFragment;
import com.jordifierro.androidbase.presentation.view.fragment.ResetPasswordFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ResetPasswordActivityTest {

    @Rule
    public final ActivityTestRule<ResetPasswordActivity> activityTestRule = new ActivityTestRule<>(
            ResetPasswordActivity.class);
    private ResetPasswordFragment resetPasswordFragment;

    @Before
    public void setUp() throws Exception {
        this.resetPasswordFragment = ((ResetPasswordFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testViewElements() throws PackageManager.NameNotFoundException {
        onView(withId(R.id.et_email)).check(matches(withHint(R.string.edittext_email)));
        onView(withId(R.id.et_password)).check(matches(withHint(R.string.edittext_newpassword)));
        onView(withId(R.id.et_password_confirmation))
                .check(matches(withHint(R.string.edittext_password_confirmation)));
        onView(withId(R.id.btn_resetpassword))
                .check(matches(withText(R.string.button_reset_password)));
    }

    @Test
    public void testResetPasswordButton() {

        onView(withId(R.id.et_email)).perform(typeText("email@test.com"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("87654321"), closeSoftKeyboard());
        onView(withId(R.id.et_password_confirmation))
                .perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btn_resetpassword)).perform(click());

        verify(this.resetPasswordFragment.getResetPasswordPresenter())
                .resetPassword("email@test.com", "87654321", "1234");
    }

}
