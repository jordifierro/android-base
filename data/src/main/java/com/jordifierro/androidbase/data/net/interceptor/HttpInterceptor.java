package com.jordifierro.androidbase.data.net.interceptor;

import android.util.Log;

import com.jordifierro.androidbase.data.net.RestApi;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HttpInterceptor implements Interceptor {

    @Inject
    public HttpInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                .addHeader("Accept", "application/vnd.railsapibase.v" + RestApi.API_VERSION)
                .build();
        return chain.proceed(request);
    }
}
