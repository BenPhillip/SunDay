package com.example.gzp.sunday.data.weather;

/**
 * Created by BenPhillip on 2018/1/23.
 */

import com.google.gson.annotations.SerializedName;

/**
 aqi": {
     "city": {
         "aqi": "67",空气质量指数
         "qlty": "良",
         "pm25": "48",
         "pm10": "77",
         "no2": "42",
         "so2": "10",
         "co": "1",
         "o3": "52"
     }
 }
 */
public class AQI {

    public AQICity city;


    public class AQICity{

        public String aqi;

        public String pm25;

        @SerializedName("qlty")
        public String level;

        public String no2;

        public String so2;

        public String co;

        public String o3;

        public String pm10;
    }
}
