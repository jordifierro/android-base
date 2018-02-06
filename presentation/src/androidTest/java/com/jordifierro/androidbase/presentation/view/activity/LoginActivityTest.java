package com.jordifierro.androidbase.presentation.view.activity;

import android.content.pm.PackageManager;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.fragment.LoginFragment;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class LoginActivityTest {

    @Rule
    public final ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(
            LoginActivity.class);
    private LoginFragment loginFragment;

    @Before
    public void setUp() throws Exception {
        this.loginFragment = ((LoginFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testViewElements() throws PackageManager.NameNotFoundException {
        onView(withId(R.id.et_email)).check(matches(withHint(R.string.edittext_email)));
        onView(withId(R.id.et_password)).check(matches(withHint(R.string.edittext_password)));
        onView(withId(R.id.btn_login)).check(matches(withText(R.string.button_login)));
        onView(withId(R.id.btn_register)).check(matches(withText(R.string.button_register)));
        onView(withId(R.id.tv_forgot_password))
                .check(matches(withText(R.string.textview_forgot_password)));
    }

    @Test
    public void testLoginButton() {

        onView(withId(R.id.et_email)).perform(typeText("email@test.com"));
        onView(withId(R.id.et_password)).perform(typeText("87654321"));
        onView(withId(R.id.btn_login)).perform(click());

        verify(this.loginFragment.getLoginPresenter()).loginUser("email@test.com", "87654321");
        verify(this.loginFragment.getLoginPresenter()).create();
        verify(this.loginFragment.getLoginPresenter()).resume();
        verifyNoMoreInteractions(this.loginFragment.getLoginPresenter());
    }

    @Test
    public void testPause() {
        this.loginFragment.onPause();
        verify(this.loginFragment.getLoginPresenter()).resume();
        verify(this.loginFragment.getLoginPresenter()).pause();
        verify(this.loginFragment.getLoginPresenter()).create();
        verifyNoMoreInteractions(this.loginFragment.getLoginPresenter());
    }

    @Test
    public void testResume() {
        this.loginFragment.onResume();
        verify(this.loginFragment.getLoginPresenter(),times(2)).resume();
        verify(this.loginFragment.getLoginPresenter()).create();
        verifyNoMoreInteractions(this.loginFragment.getLoginPresenter());
    }

    @Test
    public void testViewNotes() {
        Intents.init();

        this.loginFragment.viewNotes();

        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testRegisterButton() {
        Intents.init();

        onView(withId(R.id.btn_register)).perform(click());

        intended(hasComponent(RegisterActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testForgotPasswordClick() {
        Intents.init();

        closeSoftKeyboard();
        onView(withId(R.id.tv_forgot_password)).perform(click());

        intended(hasComponent(ResetPasswordActivity.class.getName()));
        Intents.release();
    }

}
