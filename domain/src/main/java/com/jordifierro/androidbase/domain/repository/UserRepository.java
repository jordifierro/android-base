package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<UserEntity> createUser(UserEntity user);
    Observable deleteUser(UserEntity user);
    Observable<MessageEntity> resetPassword(UserEntity user);

    Observable<UserEntity> loginUser(UserEntity user);
    Observable logoutUser(UserEntity user);
}
