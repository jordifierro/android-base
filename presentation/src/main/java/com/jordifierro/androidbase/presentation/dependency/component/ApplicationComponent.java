package com.jordifierro.androidbase.presentation.dependency.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.domain.executor.PostExecutionThread;
import com.jordifierro.androidbase.domain.executor.ThreadExecutor;
import com.jordifierro.androidbase.domain.repository.NoteRepository;
import com.jordifierro.androidbase.domain.repository.SessionRepository;
import com.jordifierro.androidbase.domain.repository.UserRepository;
import com.jordifierro.androidbase.presentation.dependency.module.ApplicationModule;
import com.jordifierro.androidbase.presentation.dependency.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    Context context();
    SharedPreferences sharedPreferences();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    SessionRepository sessionRepository();
    RestApi restApi();
    UserRepository userRepository();
    NoteRepository noteRepository();

}
