package com.example.gzp.sunday.Contract;

import com.example.gzp.sunday.Base.BaseModel;
import com.example.gzp.sunday.Base.BasePresenter;
import com.example.gzp.sunday.Base.BaseView;

import com.example.gzp.sunday.databinding.ChooseAreaBinding;
import com.example.gzp.sunday.data.db.City;
import com.example.gzp.sunday.data.db.County;
import com.example.gzp.sunday.data.db.Province;

import java.util.List;

import rx.Observable;

/**
 * Created by Ben on 2017/12/22.
 */

public interface AreaContract {
    interface View extends BaseView{
        List<String> getDataList();
        void showToast(int Rstring);
        void showProgressDialog();
        void closeProgressDialog();
        void refreshItem();
        ChooseAreaBinding getChooseAreaBinding();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void queryProvinces();

        public abstract void queryCities();

        public abstract void queryCounties();

        public abstract void queryFromserver(String type);


        public abstract void interruptHttp();



    }


    interface Model extends BaseModel{
        Observable<Province> getProvinces();
        Observable<City> getCities(int ProvinceId);
        Observable<County>getCounties(int provinceId,int cityId);
    }
}
