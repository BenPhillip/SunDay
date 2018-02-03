package com.example.gzp.sunday.Base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.litepal.util.Const;

/**
 * Created by Ben on 2017/12/22.
 */

public abstract class BaseActivity<V extends BaseView ,P extends BasePresenter<V>>
        extends AppCompatActivity implements BaseView  {

    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter=createPresenter();
        }

        presenter.attachView((V) this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }





    protected abstract P createPresenter();

    protected P getPresenter() {
        return this.presenter;
    }


    /**
     * 状态栏透明
     * xml设置：android:fitsSystemWindows="true"
     */
    protected void setStatusBar(){
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            //布局显示在状态栏上
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            //状态栏设置为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
