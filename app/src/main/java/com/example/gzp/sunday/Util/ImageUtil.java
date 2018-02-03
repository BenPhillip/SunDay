package com.example.gzp.sunday.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gzp.sunday.R;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by BenPhillip on 2018/1/31.
 */

/**
 * 图片处理
 */
public class ImageUtil {
    private final String ICON_FOLDER="weather_icon";


    private static class SingHolder{
        private static final ImageUtil INSTANCE=new ImageUtil();
    }

    public static ImageUtil getInstance(){
        return SingHolder.INSTANCE;
    }


    private ImageUtil(){

    }



    public  void handleWeatherIcon(int code, ImageView imageView) {
        Context context=imageView.getContext();
        AssetManager manager=context.getAssets();
        String[] icon_names;
        try {
            icon_names= manager.list(ICON_FOLDER);
            if(!hasCode(icon_names,code)){
                code=999;
            }

            String assetPath=ICON_FOLDER+"/"+code+".png";

            /*Glide.with(context)
                    .load("file:///android_asset/"+assetPath)
                    .into(imageView);*/
            loadImage(imageView,"file:///android_asset/"+assetPath);

        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("Utility","hanlde weather icon failure",e);
        }
    }

    public void handleWeatherBackground(int code ,ImageView imageView){
        switch (code) {
            default:

                loadImage(imageView,R.drawable.weather_default);
        }

    }


    public void loadImage(ImageView imageView,String url){
        Context context=imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);

    }

    public void loadImage(ImageView imageView,int url){
        Context context=imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);

    }

    private boolean hasCode(String[] icon_names,int code){
        return Arrays.asList(icon_names).contains(code+".png");
    }
}
