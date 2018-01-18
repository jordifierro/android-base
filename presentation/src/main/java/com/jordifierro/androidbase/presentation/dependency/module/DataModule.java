package com.jordifierro.androidbase.presentation.dependency.module;

import android.content.SharedPreferences;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.data.net.interceptor.HttpInterceptor;
import com.jordifierro.androidbase.data.repository.NoteDataRepository;
import com.jordifierro.androidbase.data.repository.SessionDataRepository;
import com.jordifierro.androidbase.data.repository.UserDataRepository;
import com.jordifierro.androidbase.data.repository.VersionDataRepository;
import com.jordifierro.androidbase.domain.repository.NoteRepository;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;
import com.jordifierro.androidbase.domain.repository.VersionRepository;
import com.jordifierro.androidbase.presentation.dependency.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @ApplicationScope
    SessionRepository provideSessionRepository(SharedPreferences sharedPreferences) {
        return new SessionDataRepository(sharedPreferences);
    }

    @Provides
    @ApplicationScope
    RestApi provideRestApi() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                                                .addInterceptor(new HttpInterceptor())
                                                .build();

        GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create());

        return new Retrofit.Builder()
                           .baseUrl(RestApi.URL_BASE)
                           .addConverterFactory(factory)
                           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                           .client(client)
                           .build()
                           .create(RestApi.class);
    }

    @Provides
    @ApplicationScope
    UserRepository provideUserRepository(RestApi restApi) {
        return new UserDataRepository(restApi);
    }

    @Provides
    @ApplicationScope
    NoteRepository provideNoteRepository(RestApi restApi) {
        return new NoteDataRepository(restApi);
    }

    @Provides
    @ApplicationScope
    VersionRepository provideVersionRepository(RestApi restApi) {
        return new VersionDataRepository(restApi);
    }

}
