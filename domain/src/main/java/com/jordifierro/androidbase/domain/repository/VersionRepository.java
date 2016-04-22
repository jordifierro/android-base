package com.jordifierro.androidbase.domain.repository;

import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import rx.Observable;

public interface VersionRepository {
    Observable<VersionEntity> checkVersionExpiration(UserEntity user);
}
