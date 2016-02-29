package com.jordifierro.androidbase.repository;

import com.jordifierro.androidbase.entity.SessionEntity;
import com.jordifierro.androidbase.entity.UserEntity;

import rx.Observable;

public interface UserRepository {
    Observable<UserEntity> createUser(String email, String password, String confirmationPassword);
    Observable deleteUser(SessionEntity session);
}
