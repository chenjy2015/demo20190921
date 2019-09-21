package com.example.myapplication.retrofit;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Chenjy
 * @create by 2019/9/20
 * <p>
 * 网络请求或返回时 缓存拦截器
 */
public class CacheIntercept implements Interceptor {

    public CacheIntercept() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        /**
         * 对request的设置用来指定有网/无网下所走的方式
         */
        Request request = chain.request();
        if (!NetworkUtils.isAvailableByPing()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
            //有网络时则根据缓存时长来决定是否发出请求
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }

        /**
         * 对response的设置用来指定有网/无网下的缓存时长
         */
        Response response = chain.proceed(request);
        if (!NetworkUtils.isAvailableByPing()) {
            //有网络情况下，超过1分钟，则重新请求，否则直接使用缓存数据
            //缓存一分钟 如果你想在有网络的情况下都直接走网络，那么只需要将其超时时间maxAge设为0即可
            int maxAge = 60;
            String cacheControl = "public,max-age=" + maxAge;
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("pragma").build();
        } else {
            //无网络时直接取缓存数据，该缓存数据保存1周
            int maxStale = 60 * 60 * 24 * 7;  //1周
            return response.newBuilder()
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .removeHeader("Pragma").build();
        }
    }
}
