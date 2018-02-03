package com.example.gzp.sunday.data.weather;

/**
 * Created by BenPhillip on 2018/1/23.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gzp.sunday.BR;


/**
 "now": {
     "dayCond": {
         "code": "101",
         "txt": "多云"
    },
     "fl": "-3",体感温度
     "hum": "71",相对湿度
     "pcpn": "0.0",降水量
     "pres": "1025",大气压强
     "tmp": "4",温度
     "vis": "8",能见度，默认单位：公里
     "wind": {
         "deg": "75",风向360角度
         "dir": "东北风",
         "sc": "微风",
         "spd": "9",风速，公里/小时
     }
 },
 */
public class Now extends BaseObservable{

    public String tmp;

    public String fl;

    public String hum;

    public String pcpn;

    public String pres;

    public Wind wind;

    public Cond cond;


    public class Wind{
        public String sc;

        public String dir;

        public String spd;

    }

    public class Cond{
        public String code;
        public String txt;
    }

    @Bindable
    public String getTmp() {
        return tmp;

    }
    public void setTmp(String tmp) {
        this.tmp = tmp;
        notifyPropertyChanged(BR.tmp);
    }

    @Bindable
    public String getHum() {
        return hum;
    }
    public void setHum(String hum) {
        this.hum = hum;
        notifyPropertyChanged(BR.hum);
    }

    @Bindable
    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
        notifyPropertyChanged(BR.wind);
    }

    @Bindable
    public Cond getCond() {
        return cond;
    }
    public void setCond(Cond cond) {
        this.cond = cond;
        notifyPropertyChanged(BR.cond);
    }

}
