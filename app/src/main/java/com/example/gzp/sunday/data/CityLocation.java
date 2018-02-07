package com.example.gzp.sunday.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BenPhillip on 2018/2/7.
 */

public class CityLocation {

    public String status;

    public Info basic;

    public class Info{
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
