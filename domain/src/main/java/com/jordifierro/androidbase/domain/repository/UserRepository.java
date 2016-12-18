package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VoidEntity;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<UserEntity> createUser(UserEntity user);
    Observable<VoidEntity> deleteUser(UserEntity user);
    Observable<MessageEntity> resetPassword(UserEntity user);

    Observable<UserEntity> loginUser(UserEntity user);
    Observable<VoidEntity> logoutUser(UserEntity user);
}
