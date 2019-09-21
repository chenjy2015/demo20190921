package com.example.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface API {

//    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
//    @GET("world/world")
//    Call<Response<String>> open(@Query("sum") String sum, @Query("page") String page);

    @GET("world/world")
    Call<Response> open(@Header ("apikey") String apiKey, @Query("sum") String sum, @Query("page") String page);
}
