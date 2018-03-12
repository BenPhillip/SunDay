package com.example.gzp.sunday.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Contract.LocationContract;
import com.example.gzp.sunday.Model.LocationModel;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.Util.MyApplication;
import com.example.gzp.sunday.data.db.MyCollection;
import com.example.gzp.sunday.data.weather.HeWeather;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BenPhillip on 2018/2/13.
 */

public class LocationPresenter extends LocationContract.Presenter {
    private static final String TAG="LocationPresenter";
    private LocationContract.Model mModel;
    private List<MyCollection> mCollectionList;
    private String cityId;
  //  private HeWeather.Weather mLocationWeather;
    private MyCollection newCollection;

    public LocationPresenter(){
        mModel=new LocationModel();
    }


    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public void getLocationCity(String location,boolean isFirst) {

        mModel.getCityId(location, WeatherService.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id->{
                    cityId=id;
                    LogUtil.v(TAG,"所在城市id为:"+cityId);

                    newCollection=new MyCollection();
                    newCollection.setCityId(cityId);
                    newCollection.setIsLocation(1);

                    //  newCollection.setWeatherInfo(new Gson().toJson(weather));

                    if(isFirst){
                        newCollection.save();
                        LogUtil.d(TAG,"is first");
                        // mLocationWeather=weather;
                    }else{
                        MyCollection oldCollection= DataSupport.where("isCollection = ?","1").findFirst(MyCollection.class);

                        if(oldCollection!=null){
                            //上一次的定位和这一次的定位是否在一个地方
                            if (!oldCollection.getCityId().equals(cityId)) {
                                if(oldCollection.getIsCollection()==1){
                                    //之前是定位，现在不是，且为收藏城市
                                    // oldCollection.IsLocation=1
                                    //oldCollection.IsCollection=1;
                                    oldCollection.setIsLocation(0);
                                    oldCollection.save();
                                    newCollection.save();
                                    LogUtil.d(TAG,"之前是定位，现在不是，且为收藏城市");
                                } else  {
                                    //之前是定位，现在不是，且没有收藏
                                    // oldCollection.IsLocation=1
                                    //oldCollection.IsCollection=0;
                                    oldCollection.delete();
                                    newCollection.save();
                                    LogUtil.d(TAG,"之前是定位，现在不是，且没有收藏");
                                }

                            }else{
                                //之前不是定位，现在是在定位
                                // oldCollection.IsLocation=0
                                //oldCollection.IsCollection=1;
                                LogUtil.d(TAG,"之前不是定位，现在是在定位");
                                oldCollection.setIsLocation(1);
                                oldCollection.save();
                            }
                        }else {
                            LogUtil.e(TAG,"数据库之前无定位数据");
                        }
                    }
                },throwable -> {
                    Toast.makeText(MyApplication.getContext(),
                            "获取城市定位失败",Toast.LENGTH_LONG)
                            .show();
                    LogUtil.e(TAG,"获取城市定位失败",throwable);
                },()->{
                    getView().addCollection(newCollection);
                    getView().updateList();
                    LogUtil.v(TAG,"定位城市数据已经更新");

                });

    }
    @Override
    public List<MyCollection> getCollectionList() {
        return mCollectionList;
    }

    @Override
    public List<MyCollection> loadCollections() {
        mCollectionList = mModel.selectCollectionsList();
        return mCollectionList;
    }
}
