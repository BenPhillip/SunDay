package com.example.gzp.sunday.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gzp.sunday.BR;
import com.example.gzp.sunday.Base.BaseFragment;
import com.example.gzp.sunday.Contract.WeatherContract;
import com.example.gzp.sunday.Presenter.WeatherPresenter;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.Util.Utility;
import com.example.gzp.sunday.View.Adapter.HourlyForecastAdapter;
import com.example.gzp.sunday.data.db.MyCollection;
import com.example.gzp.sunday.data.weather.Forecast;
import com.example.gzp.sunday.data.weather.HeWeather;

import com.example.gzp.sunday.databinding.FragmentWeatherBinding;
import com.example.gzp.sunday.service.AutoUpdateService;


/**
 * Created by BenPhillip on 2018/2/11.
 */

public class WeatherFragment extends BaseFragment<WeatherContract.View,WeatherContract.Presenter>
        implements WeatherContract.View {
    private static final String TAG="WeatherFragment";
    public static final String ARG_CITY_ID="cityId to load weather info";
    public static final String ARG_COLLECTION="Collection contains city info";

   // private  ActivityWeatherBinding mWeatherBinding;
    private FragmentWeatherBinding mBinding;
    private RecyclerView hourlyRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;


    public static WeatherFragment newInstance(MyCollection collection) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_COLLECTION,collection);

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        MyCollection collection=(MyCollection) getArguments().getParcelable(ARG_COLLECTION);

        hourlyRecyclerView= mBinding.weatherHourlyForecast
                .hourForecastRecyclerView;
        hourlyRecyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayout.HORIZONTAL, false));

        mRefreshLayout=mBinding.swipeRefresh;

        String weatherString=null;

        if(collection!=null){
            weatherString=collection.getWeatherInfo();
            mRefreshLayout.setOnRefreshListener(()->{
                getPresenter().getWeather(collection.getCityId());
            });
        }else{
            LogUtil.e(TAG,"读取城市数据为空");
        }

        mBinding.weatherTitle.navButton.setOnClickListener((view)->{
            mBinding.drawerLayout.openDrawer(GravityCompat.START);
        });


       /* SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String weatherString=preferences.getString(Utility.WEATHER,null);
       */ //String weatherId;


        if(weatherString!=null){
            HeWeather.Weather weather=Utility.getWeather(weatherString);
            // weatherId=weather.basic.weatherId;
            loadWeather(weather);
            Intent intent = new Intent(getActivity(), AutoUpdateService.class);
           getActivity().startService(intent);
        }else{
            // weatherId=getIntent().getStringExtra(Utility.WEATHER_ID);
            //LogUtil.d("weather","id:"+weatherId);
            mBinding.weatherLayout.setVisibility(View.INVISIBLE);

        }


        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected WeatherContract.Presenter createPresenter() {
        return new WeatherPresenter();
    }


    @Override
    public void showToast(int Rstring) {
        Toast.makeText(this.getActivity(), getString(Rstring), Toast.LENGTH_LONG).show();
    }

    @Override
    public FragmentWeatherBinding getWeatherLayout() {
        return this.mBinding;
    }

    @Override
    public void loadWeather(HeWeather.Weather weather){
        mBinding.weatherTitle.setBasic(weather.basic);
        LogUtil.v("weather",weather.basic.update.updateTime);
        mBinding.weatherNow.setNow(weather.now);
        if(weather.aqi!=null)
            mBinding.weatherAqi.setAqi(weather.aqi);
        //binding.weatherHourlyForecast.set
        mBinding.weatherSuggestion.setSuggestion(weather.suggestion);
        mBinding.weatherForecast.forecastLayout.removeAllViews();
        for (Forecast forecast:weather.forecastList) {
            ViewDataBinding binding=DataBindingUtil
                    .inflate(getLayoutInflater(),R.layout.forecast_item,
                            mBinding.weatherForecast.forecastLayout,false);
            binding.setVariable(BR.forecast_item,forecast);
            //binding.setVariable(BR.temp, forecast.itemTemp);
            // binding.setVariable(BR.dayCond,forecast.dayCond);\
            binding.setVariable(BR.daily_code,forecast.dayCond.code);
            mBinding.weatherForecast.forecastLayout.addView(binding.getRoot());

            // binding.executePendingBindings();
        }
        hourlyRecyclerView.setAdapter(
                new HourlyForecastAdapter(weather.hourlyForecastList, this.getActivity()));
        ImageView weatherBackground = mBinding.weatherBackground;
        int code =Integer.parseInt(weather.now.cond.code);
        Utility.loadWeatherBackground(weatherBackground,code);




    }


    @Override
    public void showWeatherLayout() {
        mBinding.weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRefreshing(boolean isTrue) {
        mRefreshLayout.setRefreshing(isTrue);
    }

    /*@Override
    public void setSwipeRefresh(String cityId) {
        mRefreshLayout.setOnRefreshListener(()->{
            getPresenter().getWeather(cityId);
        });
    }*/
}
