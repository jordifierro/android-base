package com.jordifierro.androidbase.data.net;

import com.jordifierro.androidbase.data.net.wrapper.UserWrapper;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.entity.VersionEntity;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface RestApi {

    String URL_BASE = "http://192.168.0.11:3000";
    int API_VERSION = 1;
    String VERSION_HEADER = "application/vnd.railsapibase.v" + API_VERSION;

    @POST("/users")
    Observable<Response<UserEntity>> createUser(@Body UserWrapper userWrapper);

    @DELETE("/users/0")
    Observable<Response<Void>> deleteUser(@Header("Authorization") String token);

    @POST("/users/login")
    Observable<Response<UserEntity>> doLogin(@Body UserWrapper userWrapper);

    @DELETE("/users/logout")
    Observable<Response<Void>> doLogout(@Header("Authorization") String token);

    @GET("/versions/expiration")
    Observable<Response<VersionEntity>> checkVersionExpiration(
                                                            @Header("Authorization") String token);

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
    Observable<Response<Void>> deleteNote(@Header("Authorization") String token,
                                          @Path("id") int noteId);

}
