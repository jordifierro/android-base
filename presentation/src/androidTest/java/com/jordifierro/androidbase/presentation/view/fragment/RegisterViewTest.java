package com.jordifierro.androidbase.presentation.view.fragment;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.NotesActivity;
import com.jordifierro.androidbase.presentation.view.activity.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class RegisterViewTest {

    @Rule
    public final ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(
            RegisterActivity.class);
    private RegisterFragment registerFragment;

    @Before
    public void setUp() throws Exception {
        this.registerFragment = ((RegisterFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testLoginButton() {

        onView(withId(R.id.et_email)).perform(typeText("email@test.com"));
        onView(withId(R.id.et_password)).perform(typeText("87654321"));
        onView(withId(R.id.et_password_confirmation)).perform(typeText("1234"));
        onView(withId(R.id.btn_register)).perform(click());

        verify(this.registerFragment.registerPresenter)
                .registerUser("email@test.com", "87654321", "1234");
    }

    @Test
    public void testNavigateToNotes() {
        Intents.init();

        this.registerFragment.viewNotes();

        intended(hasComponent(NotesActivity.class.getName()));
        Intents.release();
    }
}
