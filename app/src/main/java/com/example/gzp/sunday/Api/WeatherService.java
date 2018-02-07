package com.example.gzp.sunday.Api;

import com.example.gzp.sunday.data.CityLocation;
import com.example.gzp.sunday.data.weather.HeWeather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by BenPhillip on 2018/1/24.
 */

public interface WeatherService {
    public static  final String key="96476eb3362c40d5bdff4628657cecf3";

    @GET("weather")
    Observable<HeWeather> getWeather(@Query("cityid") String id, @Query("key") String key);

    @GET("https://free-api.heweather.com/s6/search")
    Observable<CityLocation> getLocation(@Query("location") String location, @Query("key") String key);
}
