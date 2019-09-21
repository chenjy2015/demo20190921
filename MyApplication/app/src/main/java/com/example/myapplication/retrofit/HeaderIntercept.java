package com.example.myapplication.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderIntercept implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        //设置具体的header内容
        builder.header("apiKey", "81bf9da930c7f9825a3c3383f1d8d766");
        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}