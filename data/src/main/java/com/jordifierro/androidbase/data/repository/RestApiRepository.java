package com.jordifierro.androidbase.data.repository;

import com.google.gson.Gson;
import com.jordifierro.androidbase.data.net.error.ResponseErrorEntity;
import com.jordifierro.androidbase.data.net.error.RestApiErrorException;
import com.jordifierro.androidbase.data.net.wrapper.ResponseErrorWrapper;

import java.io.IOException;

import retrofit2.Response;

public abstract class RestApiRepository {

    protected void handleResponseError(Response response) {
        if (!response.isSuccessful()) {
            ResponseErrorWrapper errorWrapper;
            try {
                errorWrapper = new Gson().fromJson(response.errorBody().string(), ResponseErrorWrapper.class);
                ResponseErrorEntity error = errorWrapper.getError();
                throw new RestApiErrorException(error.getMessage(), error.getStatus());
            } catch (IOException | NullPointerException e) {
                throw new RestApiErrorException(response.message(), response.code());
            }
        }
    }
}
