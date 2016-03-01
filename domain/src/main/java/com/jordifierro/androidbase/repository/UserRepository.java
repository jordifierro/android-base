package com.jordifierro.androidbase.repository;

import com.jordifierro.androidbase.entity.UserEntity;

import rx.Observable;

public interface UserRepository {
    Observable<UserEntity> createUser(UserEntity user, String password, String confirmationPassword);
    Observable deleteUser(UserEntity user);

    Observable<UserEntity> loginUser(UserEntity user, String password);
    Observable logoutUser(UserEntity user);
}
