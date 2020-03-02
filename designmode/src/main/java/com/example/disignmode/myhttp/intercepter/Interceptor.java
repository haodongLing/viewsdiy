package com.example.disignmode.myhttp.intercepter;

import java.io.IOError;
import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * created by linghaoDo on 2020-01-08
 * description:
 * <p>
 * version:
 */
public interface Interceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain {
        Request request();

        Response proceed(Request request) throws IOException;
    }
}
