package com.example.gzp.sunday.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Model.WeatherModel;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.Util.Utility;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;
import com.google.gson.annotations.Until;

import rx.Subscriber;
import rx.functions.Action1;

public class AutoUpdateService extends Service {
    public static final int HOUR=8;

    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
       // updateBackground();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = HOUR * 60 * 60 * 1000;//hour ->ms
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;

        Intent i = new Intent(this, AutoUpdateService.class);
        //封装好的startService(Intent);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);


        //撤销定时器也随即撤消了 PendingIntent
       /*
            pendingIntent =PendingIntent
                        .getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
           如果为空则定时器则还未激活
        */
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

       // LogUtil.d("service","update backend");
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString(Utility.WEATHER, null);
        if (weatherString != null) {
            HeWeather.Weather weather = Utility.getWeather(weatherString);
            String id = weather.basic.weatherId;


            new WeatherModel().requestWeather(id, WeatherService.key)
                    .subscribe((result -> {
                        if (result != null && "ok".equals(result.status)) {
                            PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this)
                                    .edit()
                                    .putString(Utility.WEATHER, new Gson().toJson(result))
                                    .apply();
                        }
                    }), (e -> {
                        e.printStackTrace();
                        LogUtil.e(AutoUpdateService.class.getName(),
                                "backend update failed ", e);
                    }));
        }
    }




}
