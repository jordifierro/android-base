package com.jordifierro.androidbase.presentation.view;

import com.jordifierro.androidbase.domain.entity.NoteEntity;

public interface NoteEditView extends BaseView {

    void showNote(NoteEntity note);
    int getNoteId();

}
