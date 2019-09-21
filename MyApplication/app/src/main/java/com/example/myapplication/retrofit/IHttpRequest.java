package com.example.myapplication.retrofit;

import javax.net.ssl.SSLSocketFactory;

public interface IHttpRequest {

    SSLSocketFactory getSSLSocketFactory() throws Exception;
}
