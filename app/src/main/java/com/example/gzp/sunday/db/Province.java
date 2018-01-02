package com.example.gzp.sunday.db;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Ben on 2017/6/11.
 * 省级
 */

public class Province extends DataSupport {
    private int id;
    @SerializedName("name")
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
