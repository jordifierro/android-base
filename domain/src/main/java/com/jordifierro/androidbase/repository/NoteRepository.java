package com.jordifierro.androidbase.repository;

import com.jordifierro.androidbase.entity.NoteEntity;
import com.jordifierro.androidbase.entity.SessionEntity;
import com.jordifierro.androidbase.entity.UserEntity;

import java.util.List;

import rx.Observable;

public interface NoteRepository {
    Observable<NoteEntity> createNote(SessionEntity session, NoteEntity note);
    Observable<NoteEntity> getNote(SessionEntity session, int noteId);
    Observable<List<NoteEntity>> getNotes(SessionEntity session);
    Observable<NoteEntity> updateNote(SessionEntity session, NoteEntity note);
    Observable deleteNote(SessionEntity session, int noteId);
}
