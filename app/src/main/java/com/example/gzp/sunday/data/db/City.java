package com.example.gzp.sunday.data.db;

import com.google.gson.annotations.SerializedName;

         import org.litepal.crud.DataSupport;

/**
 * + * Created by Ben on 2017/6/11.
 * + * 市级
 * +
 */

public class City extends DataSupport {

    private int id;
    @SerializedName("name")
    private String cityName;
    private int cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}