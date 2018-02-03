package com.example.gzp.sunday.data.weather;



import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BenPhillip on 2018/1/23.
 */

public class HeWeather extends BaseObservable {
    @SerializedName("HeWeather")
    public List<Weather> weather;



    public class Weather{
        public String status;

        public Basic basic;

        public AQI aqi;

        public Now now;

        public Suggestion suggestion;

        @SerializedName("daily_forecast")
        public List<Forecast> forecastList;

        @SerializedName("hourly_forecast")
        public List<HourlyForecast> hourlyForecastList;
    }

}
