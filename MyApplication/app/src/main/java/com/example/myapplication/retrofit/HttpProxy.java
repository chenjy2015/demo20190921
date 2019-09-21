package com.example.myapplication.retrofit;

import com.example.myapplication.MyApplication;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class HttpProxy implements IHttpProxy {

    private HttpRequestManager httpRequestManager;

    private API api;

    private BookService bookService;

    private HttpProxy() {
        httpRequestManager = HttpRequestManager.getInstance().init(MyApplication.app);
        api = httpRequestManager.create(API.class);
        bookService = httpRequestManager.create(BookService.class);
    }

    private static class Singleton {
        private static HttpProxy instance = new HttpProxy();
    }

    public static HttpProxy getInstance() {
        return Singleton.instance;
    }

    public Call<Response> request() {
        return api.open("81bf9da930c7f9825a3c3383f1d8d766", "10", "1");
    }

    public Observable<Response<ResponseBody>> getBook(int id) {
        return bookService.getBook(id);
    }

    public Observable<Response<ResponseBody>> getInfo(String key, String value){
        return bookService.getInfo(key,value);
    }
}
