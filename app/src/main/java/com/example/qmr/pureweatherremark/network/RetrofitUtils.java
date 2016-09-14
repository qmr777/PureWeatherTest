package com.example.qmr.pureweatherremark.network;

import com.example.qmr.pureweatherremark.utils.AppConfig;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by qmr on 2016/9/7.
 *
 */
public class RetrofitUtils {

    static Gson gson = new Gson();

    static OkHttpClient okHttpClientWithBaiduHeader = new OkHttpClient.Builder()
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("apikey", AppConfig.HEFENG_KEY)
                            .build();
                    return chain.proceed(request);
                }
            }).build();

    public static Retrofit GetStringRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.HEFENG_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit GetGsonRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClientWithBaiduHeader)
                .baseUrl(AppConfig.HEFENG_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}
