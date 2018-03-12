package com.example.gzp.sunday.Contract;

import com.example.gzp.sunday.Base.BaseModel;
import com.example.gzp.sunday.Base.BasePresenter;
import com.example.gzp.sunday.Base.BaseView;
import com.example.gzp.sunday.data.db.MyCollection;
import com.example.gzp.sunday.data.weather.HeWeather;

import java.util.List;

import rx.Observable;

/**
 * Created by BenPhillip on 2018/2/13.
 */

public interface LocationContract {
    interface Model extends BaseModel{
        Observable<String> getCityId(String location, String key);
        Observable<HeWeather.Weather> requestWeather(String cityid, String key);
        List<MyCollection> selectCollectionsList();

    }
    interface View extends BaseView{

        void addCollection(MyCollection collection);
        void updateList();

    }

    abstract class Presenter extends BasePresenter<View>{
        public abstract void getLocationCity(String location,boolean isFirst);
       // public abstract void getWeather(String cityId);
        public abstract String getCityId();
        public abstract List<MyCollection> getCollectionList();
        public abstract List<MyCollection>  loadCollections();
        //public abstract HeWeather.Weather getLocationWeather();
    }
}
