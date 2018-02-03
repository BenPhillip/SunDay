package com.example.gzp.sunday.Util;



import com.example.gzp.sunday.Api.AreaService;
import com.example.gzp.sunday.Api.WeatherService;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ben on 2017/12/9.
 */

/**
 * 网络请求
 */
public class HttpUtil {
    private static final int DEFAULT_TIMEOUT = 5;
    private static final String BASE_URL = "http://guolin.tech/api/";
    private AreaService mAreaService;
    private WeatherService mWeatherService;

    private HttpUtil(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client=new OkHttpClient
                .Builder()
                .addInterceptor(logging) //拦截请求，输出日志
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mAreaService = retrofit.create(AreaService.class);
        mWeatherService=retrofit.create(WeatherService.class);
    }
    private static class SingleHolder{
        private static final HttpUtil INSTANCE=new HttpUtil();
    }

    public static HttpUtil getInstance(){
        return SingleHolder.INSTANCE;
    }

    public AreaService getAreaService(){
        return this.mAreaService;
    }
    public WeatherService getWeatherService(){return this.mWeatherService;}






}
