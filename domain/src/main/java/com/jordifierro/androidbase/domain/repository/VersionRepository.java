package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import rx.Observable;

public interface VersionRepository {
    Observable<MessageEntity> checkVersionExpiration(UserEntity user);
}
