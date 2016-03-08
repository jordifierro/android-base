package com.jordifierro.androidbase.data.net.error;

public class RestApiErrorException extends RuntimeException {

    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int NOT_FOUND = 404;
    public static final int UNPROCESSABLE_ENTITY = 422;
    public static final int UPGRADE_REQUIRED = 426;
    public static final int INTERNAL_SERVER_ERROR = 500;

    private int statusCode;

    public RestApiErrorException(String detailMessage, int statusCode) {
        super(detailMessage);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
