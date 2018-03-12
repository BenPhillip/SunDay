package com.example.gzp.sunday.Presenter;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Model.WeatherModel;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.View.LocationActivity;
import com.example.gzp.sunday.View.WeatherActivity;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by BenPhillip on 2018/1/27.
 */

public class WeatherPresenter extends WeatherContract.Presenter  {
    private WeatherContract.Model mModel;




    public WeatherPresenter(){
        mModel=new WeatherModel();
    }

    /**
     * 获取天气信息
     * @param cityid 要查询的地区id
     */
    @Override
    public void getWeather(String cityid) {
        mModel.requestWeather(cityid, WeatherService.key)
                .subscribe(new Subscriber<HeWeather.Weather>() {
                    @Override
                    public void onCompleted() {
                        getView().showToast(R.string.update_success);
                        getView().showWeatherLayout();
                        getView().setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showToast(R.string.update_weather_failure);
                        getView().setRefreshing(false);
                        LogUtil.e(getClass().getName(),e.getMessage());
                    }

                    @Override
                    public void onNext(HeWeather.Weather weather) {
                        if(weather!=null&&"ok".equals(weather.status)){
                           mModel.saveWeatherInfo(weather);
                           getView().loadWeather(weather);
                        }else{
                            Observable.error(new Throwable("天气信息错误"));
                        }
                    }
                });
    }
/*
    @Override
    public void getLocationCity(String location) {
        new WeatherModel().getCityInfo(location, WeatherService.key)
                .subscribe(result -> {
                    if(result.status.equals("ok")){
                        String id=result.basic.cityId;
                        getView().setSwipeRefresh(id);
                        getWeather(id);
                    }
                });
    }
*/



}
