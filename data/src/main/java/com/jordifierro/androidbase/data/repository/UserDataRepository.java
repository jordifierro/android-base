package com.jordifierro.androidbase.data.repository;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.net.wrapper.UserWrapper;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.repository.UserRepository;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

public class UserDataRepository extends RestApiRepository implements UserRepository {

    private final RestApi restApi;

    @Inject
    public UserDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<UserEntity> createUser(UserEntity user) {
        return this.restApi.createUser(user)
                .map(new Func1<Response<UserEntity>, UserEntity>() {
                    @Override
                    public UserEntity call(Response<UserEntity> userEntityResponse) {
                        handleResponseError(userEntityResponse);
                        return userEntityResponse.body();
                    }
                });
    }

    @Override
    public Observable<Void> deleteUser(final UserEntity user) {
        return this.restApi.deleteUser(user.getAuthToken())
                .map(new Func1<Response<Void>, Void>() {
                    @Override
                    public Void call(Response<Void> voidResponse) {
                        handleResponseError(voidResponse);
                        return null;
                    }
                });
    }

    @Override
    public Observable<UserEntity> loginUser(UserEntity user) {
        return this.restApi.doLogin(new UserWrapper(user))
                .map(new Func1<Response<UserEntity>, UserEntity>() {
                    @Override
                    public UserEntity call(Response<UserEntity> userEntityResponse) {
                        handleResponseError(userEntityResponse);
                        return userEntityResponse.body();
                    }
                });
    }

    @Override
    public Observable<Void> logoutUser(UserEntity user) {
        return this.restApi.doLogout(user.getAuthToken())
                .map(new Func1<Response<Void>, Void>() {
                    @Override
                    public Void call(Response<Void> voidResponse) {
                        handleResponseError(voidResponse);
                        return null;
                    }
                });
    }
}
