package com.jordifierro.androidbase.presentation.view;

import com.jordifierro.androidbase.domain.entity.NoteEntity;

public interface NoteDetailView extends BaseView {

    void showNote(NoteEntity note);
    int getNoteId();

}
