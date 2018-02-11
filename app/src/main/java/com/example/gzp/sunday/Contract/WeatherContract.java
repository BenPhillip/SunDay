package com.example.gzp.sunday.Contract;

import android.content.Context;
import android.content.SharedPreferences;

import com.baidu.location.LocationClient;
import com.example.gzp.sunday.Base.BaseModel;
import com.example.gzp.sunday.Base.BasePresenter;
import com.example.gzp.sunday.Base.BaseView;
import com.example.gzp.sunday.data.CityLocation;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.example.gzp.sunday.databinding.ActivityWeatherBinding;

import rx.Observable;

/**
 * Created by BenPhillip on 2018/1/24.
 */

public interface WeatherContract {

    interface Model extends BaseModel{
        Observable<HeWeather.Weather> requestWeather(String cityid, String key);

        Observable<CityLocation.Location> getCityInfo(String location, String key);
    }
    interface View extends BaseView{
        void showToast(int Rstring);
        ActivityWeatherBinding getWeatherLayout();

        void loadWeather(HeWeather.Weather weather);
        void saveWeatherInfo(String weatherString);
        void showWeatherLayout();
        void setRefreshing(boolean isTrue);
        void setSwipeRefresh(String cityId);
    }

    abstract class Presenter extends BasePresenter<View>{
        public abstract void getWeather(String cityid);
        public abstract void getLocationCity(String location);

    }
}
