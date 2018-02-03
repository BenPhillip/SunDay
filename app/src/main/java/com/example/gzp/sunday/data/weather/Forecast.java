package com.example.gzp.sunday.data.weather;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gzp.sunday.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BenPhillip on 2018/1/23.
 */

public class Forecast extends BaseObservable {
    @SerializedName("date")
    public String itemDate;

    @SerializedName("tmp")
    public Temp itemTemp;

    @SerializedName("cond")
    public Cond dayCond;

    public class Temp{
        public String max;
        public String min;
    }

    public class Cond{
        @SerializedName("txt_d")
        public String info;
        @SerializedName("code_d")
        public int code;
    }

    @Bindable
    public String getItemDate() {
        return this.itemDate;
    }
    @Bindable
    public Temp getItemTemp() {
        return this.itemTemp;
    }
    @Bindable
    public Cond getDayCond() {
        return dayCond;
    }


    public void setDate(String date) {
        this.itemDate = date;
        notifyPropertyChanged(BR.itemDate);
    }

    public void setTemp(Temp temp) {
        this.itemTemp = temp;
        notifyPropertyChanged(BR.itemTemp);
    }

    public void setDayCond(Cond dayCond) {
        this.dayCond = dayCond;
        notifyPropertyChanged(BR.dayCond);
    }
}
