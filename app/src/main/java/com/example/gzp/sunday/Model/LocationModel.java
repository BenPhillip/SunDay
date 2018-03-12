package com.example.gzp.sunday.Model;

import com.example.gzp.sunday.Contract.LocationContract;
import com.example.gzp.sunday.Util.HttpUtil;
import com.example.gzp.sunday.data.CityLocation;
import com.example.gzp.sunday.data.db.MyCollection;
import com.example.gzp.sunday.data.weather.HeWeather;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by BenPhillip on 2018/2/13.
 */

public class LocationModel implements LocationContract.Model {
    @Override
    public Observable<String> getCityId(String location, String key) {
        return HttpUtil.getInstance().getWeatherService()
                .getLocation(location,key)
                .subscribeOn(Schedulers.io())
                .map(cityLocation -> {
                    CityLocation.Location city= cityLocation.locations.get(0);
                    return city.basic.cityId;
        });

    }

    @Override
    public Observable<HeWeather.Weather> requestWeather(String cityid, String key) {
        return HttpUtil.getInstance().getWeatherService()
                .getWeather(cityid,key)
                .subscribeOn(Schedulers.io())
                .map(heWeather-> {
                        return heWeather.weather.get(0);
                });


    }

    @Override
    public List<MyCollection> selectCollectionsList() {
        return DataSupport.order("isCollection desc").find(MyCollection.class);
    }
}
