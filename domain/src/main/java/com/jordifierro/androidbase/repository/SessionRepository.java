package com.jordifierro.androidbase.repository;

import com.jordifierro.androidbase.entity.UserEntity;

import rx.Observable;

public interface SessionRepository {
    UserEntity getCurrentUser();
    void setCurrentUser(UserEntity user);
    void invalidateSession();
}
