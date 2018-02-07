package com.example.gzp.sunday.Util;

import android.app.Application;
import android.content.Context;

import android.databinding.BindingAdapter;

import android.widget.ImageView;

import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;


/**
 * Created by Ben on 2017/12/9.
 * 工具类
 */

public class Utility {
    public static final String WEATHER_ID="weather_id";
    public static final String WEATHER = "weather";

    @BindingAdapter("android:background")
    public static void loadWeatherIcon(ImageView imageView, int code) {
        ImageUtil.getInstance().handleWeatherIcon(code, imageView);
    }

    public static void loadWeatherBackground(ImageView imageView, int code) {
        ImageUtil.getInstance().handleWeatherBackground(code, imageView);
    }

    public static HeWeather.Weather getWeather(String weatherString){
        return new Gson().fromJson(weatherString, HeWeather.Weather.class);
    }

}
