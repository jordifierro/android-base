package com.jordifierro.androidbase.data.net;

import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface RestApi {

    String URL_BASE = "127.0.0.1:3000";

    @FormUrlEncoded
    @POST("/users")
    Observable<Response<UserEntity>> createUser(
                                @Field("email") String email, @Field("password") String password,
                                @Field("confirmation_password") String confirmationPassword);

    @DELETE("/users")
    Observable<Response<Void>> deleteUser(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/users/login")
    Observable<Response<UserEntity>> doLogin(@Field("email") String email,
                                             @Field("password") String password);

    @DELETE("/users/logout")
    Observable<Response<Void>> doLogout(@Header("Authorization") String token);

    @POST("/notes")
    Observable<Response<NoteEntity>> createNote(@Header("Authorization") String token,
                                                @Body NoteEntity note);

    @GET("/notes/{id}")
    Observable<Response<NoteEntity>> getNote(@Header("Authorization") String token,
                                             @Path("id") int noteId);

    @GET("/notes")
    Observable<Response<List<NoteEntity>>> getNotes(@Header("Authorization") String token);

    @PUT("/notes/{id}")
    Observable<Response<NoteEntity>> updateNote(@Header("Authorization") String token,
                                                @Path("id") int noteId, @Body NoteEntity note);

    @DELETE("/notes/{id}")
    Observable<Response<Void>> deleteNote(@Header("Authorization") String token, @Path("id") int noteId);
}
