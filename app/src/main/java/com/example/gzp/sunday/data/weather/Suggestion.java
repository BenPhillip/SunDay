package com.example.gzp.sunday.data.weather;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import com.example.gzp.sunday.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BenPhillip on 2018/1/23.
 */

/*
生活指数类型
suggestion": {
"air": {
    "brf": "较差",
    "txt": "气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"
   },
"comf": {
    "brf": "较舒适",
    "txt": "白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。"
},
"cw": {
    "brf": "较不宜",
    "txt": "较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"
},
"drsg": {
    "brf": "冷",
    "txt": "天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"
},
"flu": {
    "brf": "易发",
    "txt": "天冷风大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"
},
"sport": {
    "brf": "较适宜",
    "txt": "天气较好，但考虑风力较强且气温较低，推荐您进行室内运动，若在户外运动注意防风并适当增减衣物。"
},
"trav": {
    "brf": "一般",
    "txt": "天空状况还是比较好的，但温度稍微有点低，且风稍大，会让您感觉些许凉意。外出请注意防风。"
},
"uv": {
    "brf": "最弱",
    "txt": "属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"
   }
}
 */

public class Suggestion extends BaseObservable{
    //comf：舒适度指数
    public Comf comf;

    //cw：洗车指数
    @SerializedName("cw")
    public CarWash carWash;

    //drsg：穿衣指数
    @SerializedName("drsg")
    public Dress dress;

    //flu：感冒指数
    public Flu flu;

    //sport：运动指数
    public Sport sport;

    //trav：旅游指数
    public Trav trav;

    //uv：紫外线指数
    public Uv uv;

    //air：空气污染扩散条件指数
    public Air air;





    public class Comf{
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class CarWash{
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Dress {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Flu {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Sport {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Trav {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Uv {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    public class Air {
        @SerializedName("brf")
        public String status;

        public String txt;
    }

    @Bindable
    public Comf getComf() {
        return comf;
    }
    @Bindable
    public CarWash getCarWash() {
        return carWash;
    }
    @Bindable
    public Dress getDress() {
        return dress;
    }
    @Bindable
    public Flu getFlu() {
        return flu;
    }
    @Bindable
    public Sport getSport() {
        return sport;
    }
    @Bindable
    public Trav getTrav() {
        return trav;
    }
    @Bindable
    public Uv getUv() {
        return uv;
    }
    @Bindable
    public Air getAir() {
        return air;
    }

    public void setComf(Comf comf) {
        this.comf = comf;
        notifyPropertyChanged(BR.comf);
    }

    public void setCarWash(CarWash carWash) {
        this.carWash = carWash;
        notifyPropertyChanged(BR.carWash);
    }

    public void setDress(Dress dress) {
        this.dress = dress;
        notifyPropertyChanged(BR.dress);
    }

    public void setFlu(Flu flu) {
        this.flu = flu;
        notifyPropertyChanged(BR.flu);
    }

    public void setSport(Sport sport) {
        this.sport = sport;
        notifyPropertyChanged(BR.sport);
    }

    public void setTrav(Trav trav) {
        this.trav = trav;
        notifyPropertyChanged(BR.trav);
    }

    public void setUv(Uv uv) {
        this.uv = uv;
        notifyPropertyChanged(BR.uv);
    }

    public void setAir(Air air) {
        this.air = air;
        notifyPropertyChanged(BR.air);
    }
}
