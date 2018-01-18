package com.jordifierro.androidbase.data.repository;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.net.wrapper.UserWrapper;
import com.jordifierro.androidbase.domain.entity.MessageEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VoidEntity;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDataRepository extends RestApiRepository implements UserRepository {

    private final RestApi restApi;

    @Inject
    public UserDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<UserEntity> createUser(UserEntity user) {
        return this.restApi.createUser(new UserWrapper(user))
                .map(userEntityResponse -> {
                    handleResponseError(userEntityResponse);
                    return userEntityResponse.body();
                });
    }

    @Override
    public Observable<VoidEntity> deleteUser(final UserEntity user) {
        return this.restApi.deleteUser(user.getAuthToken())
                .map(voidResponse -> {
                    handleResponseError(voidResponse);
                    return new VoidEntity();
                });
    }

    @Override
    public Observable<MessageEntity> resetPassword(UserEntity user) {
        return this.restApi.resetPassword(user.getAuthToken(), new UserWrapper(user))
                .map(messageEntityResponse -> {
                    handleResponseError(messageEntityResponse);
                    return messageEntityResponse.body();
                });
    }

    @Override
    public Observable<UserEntity> loginUser(UserEntity user) {
        return this.restApi.doLogin(new UserWrapper(user))
                .map(userEntityResponse -> {
                    handleResponseError(userEntityResponse);
                    return userEntityResponse.body();
                });
    }

    @Override
    public Observable<VoidEntity> logoutUser(UserEntity user) {
        return this.restApi.doLogout(user.getAuthToken())
                .map(voidResponse -> {
                    handleResponseError(voidResponse);
                    return new VoidEntity();
                });
    }
}
