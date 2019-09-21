package com.example.myapplication.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface BookService {

    @GET("book/{id}")
    Observable<Response<ResponseBody>> getBook(@Path("id") int id);

    @FormUrlEncoded
    @POST("book/info/")
    Observable<Response<ResponseBody>> getInfo(@Field("key") String key, @Field("value") String value);

    @FormUrlEncoded
    @POST("book/info/")
    Observable<Response<ResponseBody>> getInfo(@FieldMap Map<String, String> map);

    @Multipart
    @POST("book/upload/")
    Observable<Response<ResponseBody>> upLoadTextAndFile(@Part("textKey") RequestBody textBody, @Part("fileKey") RequestBody fileBody);

    @Multipart
    @POST("book/upload/")
    Observable<Response<ResponseBody>> upLoadTextAndFile(@PartMap Map<String, RequestBody> textBody, @PartMap Map<String, RequestBody> fileBody);

    @Multipart
    @POST("book/upload/")
    Observable<Response<ResponseBody>> upLoadTextAndFile(@Body MultipartBody multipartBody);

}
