package com.example.gzp.sunday.Util;

import android.app.Application;
import android.content.Context;

import android.databinding.BindingAdapter;

import android.widget.ImageView;


/**
 * Created by Ben on 2017/12/9.
 * 工具类
 */

public class Utility {

    @BindingAdapter("android:background")
  public static void loadWeatherIcon(ImageView imageView,int code){
      ImageUtil.getInstance().handleWeatherIcon(code,imageView);
  }

    public static void loadWeatherBackground(ImageView imageView,int code) {
        ImageUtil.getInstance().handleWeatherBackground(code,imageView);
    }

}
