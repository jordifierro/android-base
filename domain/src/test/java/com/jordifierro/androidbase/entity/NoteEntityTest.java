package com.jordifierro.androidbase.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NoteEntityTest {

    private static final int FAKE_ID = 1;
    private static final String FAKE_TITLE = "email@test.com";
    private static final String FAKE_CONTENT = "1234ABCD";

    private NoteEntity note;

    @Before
    public void setup() {
        this.note =  new NoteEntity(FAKE_ID, FAKE_TITLE, FAKE_CONTENT);
    }

    @Test
    public void testSessionConstructor() {
        assertThat(this.note.getNoteId(), is(FAKE_ID));
        assertThat(this.note.getTitle(), is(FAKE_TITLE));
        assertThat(this.note.getContent(), is(FAKE_CONTENT));
    }

    @Test
    public void testUserSetEmail() {
        this.note.setNoteId(2);
        this.note.setTitle("AnotherTitle");
        this.note.setContent("AnotherContent");

        assertThat(this.note.getNoteId(), is(2));
        assertThat(this.note.getTitle(), is("AnotherTitle"));
        assertThat(this.note.getContent(), is("AnotherContent"));
    }
}
