package com.example.gzp.sunday.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gzp.sunday.R;
import com.example.gzp.sunday.Util.LogUtil;

import com.example.gzp.sunday.View.MainActivity;
import com.example.gzp.sunday.View.WeatherActivity;
import com.example.gzp.sunday.databinding.AreaItemBinding;
import com.example.gzp.sunday.data.db.City;
import com.example.gzp.sunday.data.db.County;
import com.example.gzp.sunday.data.db.Province;

import java.util.List;



/**
 * 省市县列表适配器
 */
public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    private Context mContext;
    private ItemClickCallback mCallback;

    private List<String> dataList;
    private int currentLevel;


    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private List<Province>mProvinceList;
    private List<City>mCityList;
    private List<County>mCountyList;

    private Province selectedProvince;
    private City selectedCity;


    public AreaAdapter(List<String> dataList, Context context,ItemClickCallback callback) {
        this.dataList = dataList;
        this.mCallback=callback;
        this.mContext=context;
    }

    public interface ItemClickCallback{
        void queryCities();
        void queryCounties();
    }

    @Override
    public AreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        AreaItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.area_item, parent, false);
        return new AreaViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(AreaViewHolder holder, int position) {

        switch (currentLevel) {
            case LEVEL_PROVINCE:
                LogUtil.v("List","province--->"+dataList.get(position));
                Province province = mProvinceList.get(position);
               // holder.bind(dataList.get(position), province);
                holder.bind(province.getProvinceName(), province);
                break;
            // queryCities();
            case LEVEL_CITY:
                LogUtil.v("List","city--->"+dataList.get(position));
                City city=mCityList.get(position);
                //selectedCity =
                holder.bind(city.getCityName(), city);
              //  holder.bind(dataList.get(position), city);
                break;
            // queryCounties();
            case LEVEL_COUNTY:
                LogUtil.v("List","county--->"+dataList.get(position));
                County county = mCountyList.get(position);
                holder.bind(dataList.get(position),county);
            default:
                LogUtil.v("List","county--->"+dataList.get(position));
                holder.bind(dataList.get(position));

        }

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public int getLevel() {
        return this.currentLevel;
    }
    public void setLevel(int level){
        this.currentLevel=level;
    }


     class AreaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private AreaItemBinding mAreaItemBinding;
        private Province mProvince;
        private City mCity;
        private County mCounty;


        public AreaViewHolder(AreaItemBinding binding) {
            super(binding.getRoot());
            this.mAreaItemBinding = binding;
            binding.getRoot().setOnClickListener(this);
            //mAreaItemBinding.textView.setOnClickListener(this);
        }

        public void bind(String title) {
            mAreaItemBinding.setTitle(title);
            //mAreaItemBinding.notifyChange();

            mAreaItemBinding.executePendingBindings();
        }

        public void bind(String title, Province province) {
            this.bind(title);
            this.mProvince = province;

        }

        public void bind(String title, City city) {
            this.bind(title);
            this.mCity = city;

        }
         public void bind(String title, County county) {
             this.bind(title);
             this.mCounty=county;
             //LogUtil.v("weather",mCounty.getWeatherId());

         }



        @Override
        public void onClick(View view) {
            LogUtil.v("List",currentLevel+"");
            if (currentLevel == LEVEL_PROVINCE) {
                selectedProvince = mProvince;
                currentLevel = LEVEL_CITY;
                mCallback.queryCities();
            } else if (currentLevel == LEVEL_CITY) {
                selectedCity = mCity;
                currentLevel = LEVEL_COUNTY;
                mCallback.queryCounties();
            } else if (currentLevel == LEVEL_COUNTY) {
                String id = mCounty.getWeatherId();
                LogUtil.d("weather","put id :"+id);
                Intent intent = new Intent(mContext, WeatherActivity.class);
                intent.putExtra(WeatherActivity.WEATHER_ID, id);

                MainActivity activity = (MainActivity) mContext;
                activity.startActivity(intent);
                activity.finish();

            }
        }
    }

    public List<Province> getProvinceList() {
        return mProvinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        mProvinceList = provinceList;
    }

    public List<City> getCityList() {
        return mCityList;
    }

    public void setCityList(List<City> cityList) {
        mCityList = cityList;
    }

    public List<County> getCountyList() {
        return mCountyList;
    }

    public void setCountyList(List<County> countyList) {
        mCountyList = countyList;
    }

    public Province getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Province selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

}


