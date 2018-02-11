package com.example.gzp.sunday.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BenPhillip on 2018/2/7.
 */

public class CityLocation {
    @SerializedName("HeWeather6")
    public List<Location> locations;

    public class Location {

        public String status;

        public Info basic;

        public class Info {
            @SerializedName("cid")
            public String cityId;

            @SerializedName("cnty")
            public String country;

            @SerializedName("location")
            public String city;

            @SerializedName("parent_city")
            public String parentCity;

            @SerializedName("admin_area")
            public String province;
        }
    }
}
