package com.jordifierro.androidbase.presentation.view.fragment;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.presentation.R;
import com.jordifierro.androidbase.presentation.view.activity.NoteCreateActivity;
import com.jordifierro.androidbase.presentation.view.activity.NoteDetailActivity;
import com.jordifierro.androidbase.presentation.view.activity.NotesActivity;
import com.jordifierro.androidbase.presentation.view.activity.SettingsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NotesViewTest {

    @Rule
    public final ActivityTestRule<NotesActivity> activityTestRule = new ActivityTestRule<>(
            NotesActivity.class);
    private NotesFragment notesFragment;
    private List<NoteEntity> notes = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        this.notesFragment = ((NotesFragment) this.activityTestRule.getActivity()
                                .getFragmentManager().findFragmentById(R.id.fragment_container));
        this.notes.add(new NoteEntity(1, "First title", "First content"));
        this.notes.add(new NoteEntity(2, "Second title", "Second content"));
        this.notes.add(new NoteEntity(3, "Third title", "Third content"));
    }

    @Test
    public void testShowNotes() {

        this.activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NotesViewTest.this.notesFragment.showNotes(NotesViewTest.this.notes);
            }
        });

        onView(withText("First title")).check(matches(isDisplayed()));
        onView(withText("Second title")).check(matches(isDisplayed()));
        onView(withText("Third title")).check(matches(isDisplayed()));
    }

    @Test
    public void testNoteSelected() {
        this.activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NotesViewTest.this.notesFragment.showNotes(NotesViewTest.this.notes);
            }
        });

        onView(withText("Second title")).perform(click());

        verify(this.notesFragment.notesPresenter).onNoteSelected(any(NoteEntity.class));
    }

    @Test
    public void testNavigateToNote() {
        Intents.init();

        this.notesFragment.navigateToNoteDetail(2);

        intended(allOf(
                hasComponent(NoteDetailActivity.class.getName()),
                hasExtra(NoteDetailActivity.PARAM_NOTE_ID, 2)));
        Intents.release();
    }

    @Test
    public void testNavigateToCreateNote() {
        Intents.init();

        onView(withId(R.id.btn_create_new_note)).perform(click());

        intended(hasComponent(NoteCreateActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testNavigateToSettings() {
        Intents.init();

        onView(withId(R.id.item_settings)).perform(click());

        intended(hasComponent(SettingsActivity.class.getName()));
        Intents.release();
    }
}
