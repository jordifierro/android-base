package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import java.util.List;

import rx.Observable;

public interface NoteRepository {
    Observable<NoteEntity> createNote(UserEntity user, NoteEntity note);
    Observable<NoteEntity> getNote(UserEntity user, int noteId);
    Observable<List<NoteEntity>> getNotes(UserEntity user);
    Observable<NoteEntity> updateNote(UserEntity user, NoteEntity note);
    Observable deleteNote(UserEntity user, int noteId);
}
