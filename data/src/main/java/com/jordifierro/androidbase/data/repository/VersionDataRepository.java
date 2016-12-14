package com.jordifierro.androidbase.data.repository;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;
import com.jordifierro.androidbase.domain.repository.VersionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

@Singleton
public class VersionDataRepository extends RestApiRepository implements VersionRepository {

    private final RestApi restApi;

    @Inject
    public VersionDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<VersionEntity> checkVersionExpiration(UserEntity user) {
        return this.restApi.checkVersionExpiration(user.getAuthToken())
                .map(versionEntityResponse -> {
                    handleResponseError(versionEntityResponse);
                    return versionEntityResponse.body();
                });
    }

}
