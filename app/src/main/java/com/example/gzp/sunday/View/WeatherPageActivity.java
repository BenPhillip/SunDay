package com.example.gzp.sunday.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Model.WeatherModel;


/**
 * Created by BenPhillip on 2018/2/8.
 */

public class WeatherPageActivity extends AppCompatActivity {

    private WeatherContract.Model mModel;
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel=new WeatherModel();
        mModel.getCityInfo("auto_ip", WeatherService.key)
                .subscribe(weather->{
                    if(weather.status.equals("ok")){
                        cityId=weather.basic.cityId;
                    }
                });

    }






}
