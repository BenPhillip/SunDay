package com.example.gzp.sunday.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gzp.sunday.Base.BindViewHolder;
import com.example.gzp.sunday.R;
import com.example.gzp.sunday.data.weather.HourlyForecast;
import com.example.gzp.sunday.databinding.HourlyForecastItemBinding;

import java.util.List;

/**
 * 小时预报适配器
 */
public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder> {
    private List<HourlyForecast> mHourlyForecastList;
    private Context mContext;

    public HourlyForecastAdapter(List<HourlyForecast> hourlyForecastList, Context context) {
        this.mHourlyForecastList=hourlyForecastList;
        this.mContext=context;

    }
    @Override
    public HourlyForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        HourlyForecastItemBinding binding= DataBindingUtil
                .inflate(inflater,R.layout.hourly_forecast_item, parent,false);
        return new HourlyForecastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HourlyForecastViewHolder holder, int position) {
        HourlyForecast hourlyForecast = mHourlyForecastList.get(position);
        holder.bind(hourlyForecast);
    }



    @Override
    public int getItemCount() {
        return mHourlyForecastList.size();
    }


    class HourlyForecastViewHolder extends BindViewHolder<HourlyForecastItemBinding>{


        private HourlyForecastViewHolder(HourlyForecastItemBinding binding) {
            super(binding);

        }

        public void bind(HourlyForecast hourlyForecast) {
            getBinding().setHourly(hourlyForecast);
            getBinding().executePendingBindings();
        }
    }
}
