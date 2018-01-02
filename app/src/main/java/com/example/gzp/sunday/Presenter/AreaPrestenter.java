package com.example.gzp.sunday.Presenter;

import android.view.View;

import com.example.gzp.sunday.Contract.AreaContract;
import com.example.gzp.sunday.Model.AreaModel;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.View.Adapter.AreaAdapter;
import com.example.gzp.sunday.db.City;
import com.example.gzp.sunday.db.County;
import com.example.gzp.sunday.db.Province;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_CITY;
import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_COUNTY;
import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_PROVINCE;

/**
 * Created by Ben on 2017/12/23.
 */

public class AreaPrestenter extends AreaContract.Presenter {
    private AreaContract.Model mModel;
    private AreaAdapter mAdapter;

    private static final String TYPE_PROVINCE = "province";
    private static final String TYPE_CITY = "city";
    private static final String TYPE_COUNTY = "county";

    public AreaPrestenter(AreaAdapter areaAdapter) {
        this.mModel=new AreaModel();
        this.mAdapter=areaAdapter;
    }


    @Override
    public void queryProvinces(){
        getView().getChooseAreaBinding()
                .backButton.setVisibility(View.GONE);
        getView().getChooseAreaBinding().titleText.setText("中国");

        List<String>dataList=getView().getDataList();
        List<Province> mProvinceList = DataSupport.findAll(Province.class);
        mAdapter.setProvinceList(mProvinceList);
        dataList.clear();
        if (mProvinceList.size() > 0) {
            for (Province province :
                    mProvinceList) {
                dataList.add(province.getProvinceName());
            }
           getView().refreshItem();

            LogUtil.i("List", "load Provinces from SQLite");

        }else{
            queryFromserver(TYPE_PROVINCE);
        }
    }


    @Override
    public void queryCities() {
        Province province=mAdapter.getSelectedProvince();
        String name=province.getProvinceName();
        getView().getChooseAreaBinding()
                .backButton.setVisibility(View.VISIBLE);
        getView().getChooseAreaBinding().titleText.setText(name);

        List<String>dataList=getView().getDataList();
        List<City> mCityList=DataSupport.where("provinceid=?", String.valueOf(province.getId())).find(City.class);

        mAdapter.setCityList(mCityList);
        dataList.clear();
        if (mCityList.size() > 0) {
            for (City city : mCityList) {
                dataList.add(city.getCityName());
            }
            getView().refreshItem();

            LogUtil.i("List", "load Cities from SQLite");

        }else{
            queryFromserver(TYPE_CITY);
        }
    }

    @Override
    public void queryCounties() {
        getView().getChooseAreaBinding()
                .backButton.setVisibility(View.VISIBLE);
        City city=mAdapter.getSelectedCity();
        String title=city.getCityName();
        getView().getChooseAreaBinding().titleText.setText(title);


        List<String>dataList=getView().getDataList();
        List<County> mCountyList = DataSupport.where("cityid=?", String.valueOf(city.getCityCode())).find(County.class);
        mAdapter.setCountyList(mCountyList);
        dataList.clear();
        if (mCountyList.size() > 0) {
            for (County county : mCountyList) {
                dataList.add(county.getCountyName());
            }
            getView().refreshItem();

            LogUtil.i("List", "load Province from SQLite");

        }else{
            queryFromserver(TYPE_COUNTY);
        }
    }

    @Override
    public void queryFromserver(String type) {
        LogUtil.v("List","http request "+type);
        getView().showProgressDialog();
        if (type.equals(TYPE_PROVINCE)) {
            mModel.getProvinces()
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(province -> {
                   LogUtil.i("thread","onNext="+Thread.currentThread().getName());
               },new onError(),new onCompleted());
        } else if (type.equals(TYPE_CITY)) {
            int provinceCode=mAdapter.getSelectedProvince().getProvinceCode();
            LogUtil.v("List","P--code="+provinceCode);
            mModel.getCities(provinceCode)
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(city -> {},new onError(),new onCompleted());
        } else if (type.equals(TYPE_COUNTY)) {
            int provinceCode=mAdapter.getSelectedProvince().getProvinceCode();
            int cityCode=mAdapter.getSelectedCity().getCityCode();
            mModel.getCounties(provinceCode,cityCode)
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(county -> {},new onError(),new onCompleted());
        }
    }


    @Override
    public void interruptHttp() {

    }


    private class onCompleted implements Action0{
        @Override
        public void call() {
            LogUtil.i("thread","onCompleted="+Thread.currentThread().getName());
            if(mAdapter.getLevel()==LEVEL_PROVINCE){
                queryProvinces();
            } else if (mAdapter.getLevel() == LEVEL_CITY) {
                queryCities();
            }else{
                queryCounties();
            }
            getView().closeProgressDialog();
            getView().showToast(R.string.loadingFinish);
            // getView().refreshItem();
        }
    }

    private class onError implements Action1<Throwable> {

        @Override
        public void call(Throwable e) {
            getView().closeProgressDialog();
            LogUtil.e(AreaPrestenter.class.getName(),e.getMessage());
        }
    }





}
