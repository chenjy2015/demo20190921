package com.example.myapplication.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface IHttpProxy {

    Observable<Response<ResponseBody>> getBook(int page);
}
