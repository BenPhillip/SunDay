package com.example.gzp.sunday.Util;

import android.text.TextUtils;

import com.example.gzp.sunday.db.City;
import com.example.gzp.sunday.db.County;
import com.example.gzp.sunday.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ben on 2017/12/9.
 * 数据解析类
 */

public class Utility {
    /**
     * 解析数据和处理服务器返回的省级数据
     */
    public static void handleProvinceResponse(Province province) {
        /*if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

        Province data = new Province();
        data.setProvinceName(province.getProvinceName());
        data.setProvinceCode(province.getId());
        data.save();
        LogUtil.v("List",province.getProvinceName()+" save");


    }


    /**
     * 解析数据和处理服务器返回的市级数据
     */
    public static void  handleCityResponse(City city,int provinceId) {
       /* if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode((cityObject.getInt("id")));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/


       City data=new City();
       data.setCityName(city.getCityName());
        data.setCityCode(city.getId());
        data.setProvinceId(provinceId);
        data.save();

    }



    /**
     * 解析数据和处理服务器返回的县级数据
     */
    public static void handleCountyResponse(County county,int cityId) {
        /*if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties= new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;*/

        County data = new County();
        data.setCountyName(county.getCountyName());
        data.setCityId(cityId);
        data.setWeatherId(county.getWeatherId());
        data.save();


    }

}
