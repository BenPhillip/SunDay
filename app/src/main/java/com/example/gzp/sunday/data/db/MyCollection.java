package com.example.gzp.sunday.data.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * 用于管理收藏的城市
 * 实现LitePal和序列化
 */

public class MyCollection extends DataSupport implements Parcelable{

    private int id;

    private String cityId;

    private int isLocation;

    private int isCollection;

    private String weatherInfo;



    public static final Creator<MyCollection> CREATOR = new Creator<MyCollection>() {
        @Override
        public MyCollection createFromParcel(Parcel in) {
            MyCollection collection=new MyCollection();
            collection.id = in.readInt();
            collection.cityId = in.readString();
            collection.isLocation = in.readInt();
            collection.isCollection=in.readInt();
            collection.weatherInfo = in.readString();
            return collection;
        }

        @Override
        public MyCollection[] newArray(int size) {
            return new MyCollection[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(int location) {
        isLocation = location;
    }



    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cityId);
        dest.writeInt(isLocation);
        dest.writeInt(isCollection);
        dest.writeString(weatherInfo);
    }
}
