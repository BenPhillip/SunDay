package com.example.gzp.sunday.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.litepal.util.Const;

/**
 * Created by Ben on 2017/12/22.
 */

public abstract class BaseActivity<V extends BaseView ,P extends BasePresenter<V>>
        extends AppCompatActivity implements BaseView  {

    protected P presenter;

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

}
