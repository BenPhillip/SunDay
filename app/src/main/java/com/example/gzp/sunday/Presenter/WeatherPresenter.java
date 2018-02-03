package com.example.gzp.sunday.Presenter;


import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Model.WeatherModel;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by BenPhillip on 2018/1/27.
 */

public class WeatherPresenter extends WeatherContract.Presenter {
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
                            String weatherString=new Gson().toJson(weather);
                           getView().saveWeatherInfo(weatherString);
                           getView().loadWeather(weather);
                        }else{
                            Observable.error(new Throwable("天气信息错误"));
                        }
                    }
                });
    }


}
