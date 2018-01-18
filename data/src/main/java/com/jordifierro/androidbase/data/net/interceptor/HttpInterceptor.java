package com.jordifierro.androidbase.data.net.interceptor;

import com.jordifierro.androidbase.data.net.RestApi;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Inject
    public HttpInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                .addHeader("Accept", RestApi.VERSION_HEADER)
                .build();
        return chain.proceed(request);
    }

}
