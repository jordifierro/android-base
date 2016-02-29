package com.jordifierro.androidbase.repository;

import com.jordifierro.androidbase.entity.SessionEntity;
import com.jordifierro.androidbase.entity.UserEntity;

import rx.Observable;

public interface SessionRepository {
    Observable<SessionEntity> loginUser(String email, String password);
    Observable logoutUser(SessionEntity session);

    boolean isUserLoggedIn();
    SessionEntity getCurrentUser();
    void invalidateSession();
}
