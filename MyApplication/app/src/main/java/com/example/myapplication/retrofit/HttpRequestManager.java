package com.example.myapplication.retrofit;


import android.content.Context;

import com.example.myapplication.GsonIntegerDefaultAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequestManager implements IHttpRequest {

    //证书中的公钥
    public static final String PUB_KEY = "3082010a0282010100d52ff5dd432b3a05113ec1a7065fa5a80308810e4e181cf14f7598c8d553cccb7d5111fdcdb55f6ee84fc92cd594adc1245a9c4cd41cbe407a919c5b4d4a37a012f8834df8cfe947c490464602fc05c18960374198336ba1c2e56d2e984bdfb8683610520e417a1a9a5053a10457355cf45878612f04bb134e3d670cf96c6e598fd0c693308fe3d084a0a91692bbd9722f05852f507d910b782db4ab13a92a7df814ee4304dccdad1b766bb671b6f8de578b7f27e76a2000d8d9e6b429d4fef8ffaa4e8037e167a2ce48752f1435f08923ed7e2dafef52ff30fef9ab66fdb556a82b257443ba30a93fda7a0af20418aa0b45403a2f829ea6e4b8ddbb9987f1bf0203010001";

    private static final String baseUrl = "https://api.douban.com/v2/";


    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;

    private Context mContext;

    private HttpRequestManager() {
    }

    private static class Singleton {
        private static HttpRequestManager INSTANCE = new HttpRequestManager();
    }

    public static HttpRequestManager getInstance() {
        return Singleton.INSTANCE;
    }

    public HttpRequestManager init(Context context) {
        this.mContext = context;
        initOKHttp();
        initRetrofit();
        return this;
    }

    private void initOKHttp() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        initIntercept(okHttpClientBuilder);
        mOkHttpClient = okHttpClientBuilder.build();
    }

    private void initIntercept(OkHttpClient.Builder okHttpClientBuilder) {
        //缓存地址
        File cacheFile = new File(mContext.getExternalCacheDir(), "HttpCache");
        //大小50Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        //设置缓存方式、时长、地址
        CacheIntercept cacheIntercept = new CacheIntercept();
        okHttpClientBuilder.addInterceptor(cacheIntercept);
        okHttpClientBuilder.addNetworkInterceptor(cacheIntercept);
        okHttpClientBuilder.cache(cache);

        try {
            okHttpClientBuilder.sslSocketFactory(getSSLSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //init header
        HeaderIntercept headerIntercept = new HeaderIntercept();
        okHttpClientBuilder.addInterceptor(headerIntercept);
        //init logger
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(loggingInterceptor);
    }


    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public Gson buildGson() {
        return new GsonBuilder().serializeNulls().registerTypeAdapter(int.class, new GsonIntegerDefaultAdapter()).create();
    }

    public <T> T create(Class<T> cls) {
        return mRetrofit.create(cls);
    }

    @Override
    public SSLSocketFactory getSSLSocketFactory() throws Exception {


        //创建一个不验证证书链的证书信任管理器。
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {


            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            //客户端并为对ssl证书的有效性进行校验
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                if (chain == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }

                if (!(chain.length > 0)) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                }

                if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                }

                // Perform customary SSL/TLS checks
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
                // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

                String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                // Pin it!
                final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                if (!expected) {
                    throw new CertificateException("checkServerTrusted: Expected public key: "
                            + PUB_KEY + ", got public key:" + encoded);
                }
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    }

}
