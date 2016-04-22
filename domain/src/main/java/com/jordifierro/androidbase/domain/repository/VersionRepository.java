package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;

import rx.Observable;

public interface VersionRepository {
    Observable<VersionEntity> checkVersionExpiration(UserEntity user);
}
