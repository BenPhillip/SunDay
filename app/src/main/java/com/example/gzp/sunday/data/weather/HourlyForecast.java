package com.example.gzp.sunday.data.weather;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gzp.sunday.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BenPhillip on 2018/1/24.
 */
/*
"hourly_forecast": [
 {
    "cond": {
        "code": "103",
        "txt": "晴间多云"
    },
    "date": "2018-02-01 19:00",
    "hum": "37",
    "pop": "0",
    "pres": "1031",
    "tmp": "4",
    "wind": {
        "deg": "290",
        "dir": "西北风",
        "sc": "微风",
        "spd": "6"
    }
},


],
 */
public class HourlyForecast extends BaseObservable {
    @SerializedName("cond")
    public Cond hourlyCond;

    @SerializedName("date")
    public String hour;

    @SerializedName("tmp")
    public String hourlyTmp;


    public class Cond{

        public int code;

        public String txt;
    }

    @Bindable
    public Cond getHourlyCond() {
        return hourlyCond;
    }
    @Bindable

    public String getHour() {
        return hour;
    }
    @Bindable

    public String getHourlyTmp() {
        return hourlyTmp;
    }

    public void setHourlyCond(Cond hourlyCond) {
        this.hourlyCond = hourlyCond;
        notifyPropertyChanged(BR.hourlyCond);
    }

    public void setHour(String hour) {
        this.hour = hour;
        notifyPropertyChanged(BR.hour);
    }

    public void setHourlyTmp(String hourlyTmp) {
        this.hourlyTmp = hourlyTmp;
        notifyPropertyChanged(BR.hourlyTmp);
    }
}
