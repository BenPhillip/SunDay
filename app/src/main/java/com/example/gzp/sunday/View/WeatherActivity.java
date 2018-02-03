package com.example.gzp.sunday.View;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gzp.sunday.BR;
import com.example.gzp.sunday.Base.BaseActivity;

import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Presenter.WeatherPresenter;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.Util.Utility;
import com.example.gzp.sunday.View.Adapter.HourlyForecastAdapter;
import com.example.gzp.sunday.data.weather.Forecast;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.example.gzp.sunday.databinding.ActivityWeatherBinding;
import com.google.gson.Gson;

/**
 * Created by BenPhillip on 2018/1/27.
 */

public class WeatherActivity extends BaseActivity<WeatherContract.View,WeatherContract.Presenter>
        implements WeatherContract.View {
    public static final String DEFAULT_WEATHER="weather";
    public static final String WEATHER_ID="weather_id";
    private ActivityWeatherBinding mWeatherBinding;
    private RecyclerView hourlyRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        mWeatherBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather);
        hourlyRecyclerView= mWeatherBinding.weatherHourlyForecast
                .hourForecastRecyclerView;
        hourlyRecyclerView
                .setLayoutManager(new LinearLayoutManager(this,
                        LinearLayout.HORIZONTAL, false));

        mRefreshLayout=mWeatherBinding.swipeRefresh;


        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=preferences.getString(DEFAULT_WEATHER,null);
        String weatherId;
        if(weatherString!=null){
            HeWeather.Weather weather=new Gson()
                    .fromJson(weatherString, HeWeather.Weather.class);
            weatherId=weather.basic.weatherId;
           loadWeather(weather);
        }else{
            weatherId=getIntent().getStringExtra(WEATHER_ID);
            LogUtil.d("weather","id:"+weatherId);
            mWeatherBinding.weatherLayout.setVisibility(View.INVISIBLE);
            getPresenter().getWeather(weatherId);
        }

        mRefreshLayout.setOnRefreshListener(()->{
            getPresenter().getWeather(weatherId);
        });



    }

    @Override
    public void loadWeather(HeWeather.Weather weather){
        mWeatherBinding.weatherTitle.setBasic(weather.basic);
        LogUtil.v("weather",weather.basic.update.updateTime);
        mWeatherBinding.weatherNow.setNow(weather.now);
        if(weather.aqi!=null)
            mWeatherBinding.weatherAqi.setAqi(weather.aqi);
        //binding.weatherHourlyForecast.set
        mWeatherBinding.weatherSuggestion.setSuggestion(weather.suggestion);
        mWeatherBinding.weatherForecast.forecastLayout.removeAllViews();
        for (Forecast forecast:weather.forecastList) {
           ViewDataBinding binding=DataBindingUtil
                   .inflate(LayoutInflater.from(this),R.layout.forecast_item,
                   mWeatherBinding.weatherForecast.forecastLayout,false);
            binding.setVariable(BR.forecast_item,forecast);
            //binding.setVariable(BR.temp, forecast.itemTemp);
           // binding.setVariable(BR.dayCond,forecast.dayCond);\
            binding.setVariable(BR.daily_code,forecast.dayCond.code);
            mWeatherBinding.weatherForecast.forecastLayout.addView(binding.getRoot());

           // binding.executePendingBindings();
        }
        hourlyRecyclerView.setAdapter(
                new HourlyForecastAdapter(weather.hourlyForecastList, this));
        ImageView weatherBackground = mWeatherBinding.weatherBackground;
        int code =Integer.parseInt(weather.now.cond.code);
        Utility.loadWeatherBackground(weatherBackground,code);


    }

    @Override
    public void saveWeatherInfo(String weatherString) {
        PreferenceManager
                .getDefaultSharedPreferences(WeatherActivity.this)
                .edit()
                .putString(DEFAULT_WEATHER,weatherString)
                .apply();
    }

    @Override
    public void showWeatherLayout() {
        mWeatherBinding.weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRefreshing(boolean isTrue) {
        mRefreshLayout.setRefreshing(isTrue);
    }

    @Override
    protected WeatherContract.Presenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public void showToast(int Rstring) {
        Toast.makeText(this, getString(Rstring),Toast.LENGTH_LONG).show();
    }

    @Override
    public ActivityWeatherBinding getWeatherLayout() {
        return this.mWeatherBinding;
    }

}


