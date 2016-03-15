package com.jordifierro.androidbase.presentation.view.fragment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.NoteCreateActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NoteCreateViewTest {

    @Rule
    public final ActivityTestRule<NoteCreateActivity> activityTestRule = new ActivityTestRule<>(
            NoteCreateActivity.class);
    private NoteCreateFragment noteCreateFragment;

    @Before
    public void setUp() throws Exception {
        this.noteCreateFragment = ((NoteCreateFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    @Test
    public void testCreateNote() {

        onView(withId(R.id.et_title)).perform(typeText("Title"));
        onView(withId(R.id.et_content)).perform(typeText("Content"));
        onView(withId(R.id.btn_submit)).perform(click());

        verify(this.noteCreateFragment.noteCreatePresenter).createButtonPressed("Title", "Content");
    }

}
