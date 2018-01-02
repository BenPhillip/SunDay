package com.example.gzp.sunday.Model;

import com.example.gzp.sunday.Contract.AreaContract;
import com.example.gzp.sunday.Util.HttpUtil;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.db.City;
import com.example.gzp.sunday.db.County;
import com.example.gzp.sunday.db.Province;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ben on 2017/12/22.
 */

public class AreaModel implements AreaContract.Model {
    @Override
    public Observable<Province> getProvinces() {
        return HttpUtil.getInstance().getAreaService()
                .getProvince()
                .compose(new LiftAllTransformer<Province>())
                .doOnNext(province->{
                    LogUtil.d("thread","doOnNext="+Thread.currentThread().getName());
                    saveProvince(province);
                });

    }

    @Override
    public Observable<City> getCities(int provinceId) {
        return HttpUtil.getInstance().getAreaService()
                .getCity(provinceId)
                .compose(new LiftAllTransformer<City>())
                .doOnNext(city -> {
                    saveCity(city,provinceId);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<County> getCounties(int provinceId,int cityId) {
        return HttpUtil.getInstance().getAreaService()
                .getCounty(provinceId,cityId)
                .compose(new LiftAllTransformer<County>())
               .doOnNext(county -> {
                    saveCounty(county,cityId);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


    private void saveProvince(Province province) {
        Province data = new Province();
        data.setProvinceName(province.getProvinceName());
        data.setProvinceCode(province.getId());
        data.save();
        LogUtil.v("List",province.getProvinceName()+" save");
    }


    private void saveCity(City city, int provinceId) {
        City data=new City();
        data.setCityName(city.getCityName());
        data.setCityCode(city.getId());
        data.setProvinceId(provinceId);
        data.save();
    }


    private void saveCounty(County county, int cityId) {
        County data = new County();
        data.setCountyName(county.getCountyName());
        data.setCityId(cityId);
        data.setWeatherId(county.getWeatherId());
        data.save();
    }

    private class LiftAllTransformer<T> implements Observable.Transformer<List<T>,T>{



        @Override
        public Observable<T> call(Observable<List<T>> observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                   /* .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            //TODO: ProgressBar View.VISIBLE
                            mCallBack.onStart();
                        }
                    })*/
                   // .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.newThread())
                    .flatMap(new Func1<List<T>, Observable<T>>() {
                        @Override
                        public Observable<T> call(List<T> list) {
                            LogUtil.v("List","size="+list.size());
                            LogUtil.d("thread","flatMap="+Thread.currentThread().getName());
                            return Observable.from(list);
                        }
                    });
                    //.observeOn(Schedulers.io());
        }
    }

}
