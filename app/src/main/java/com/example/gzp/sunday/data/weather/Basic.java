package com.example.gzp.sunday.data.weather;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gzp.sunday.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BenPhillip on 2018/1/23.
 */

/**
  "basic": {
     "city": "苏州",
     "cnty": "中国",
     "id": "CN101190401",
     "lat": "31.29937935",
     "lon": "120.61958313",
     "update": {
         "loc": "2018-01-23 13:53",
         "utc": "2018-01-23 05:53"
     }
 }
 */
public class Basic extends BaseObservable {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }

    @Bindable
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
        notifyPropertyChanged(BR.cityName);
    }

    @Bindable
    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
        notifyPropertyChanged(BR.update);
    }
}
