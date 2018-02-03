package com.example.gzp.sunday.Model;

import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Util.HttpUtil;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by BenPhillip on 2018/1/24.
 */

public class WeatherModel implements WeatherContract.Model {

    @Override
    public Observable<HeWeather.Weather> requestWeather(String cityid, String key) {
        return HttpUtil.getInstance().getWeatherService()
                .getWeather(cityid, key)
                .subscribeOn(Schedulers.io())
                .map(new Func1<HeWeather, HeWeather.Weather>() {
                    @Override
                    public HeWeather.Weather call(HeWeather heWeather) {
                        return heWeather.weather.get(0);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
