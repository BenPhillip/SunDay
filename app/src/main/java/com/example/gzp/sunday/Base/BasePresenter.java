package com.example.gzp.sunday.Base;

/**
 * Created by Ben on 2017/12/22.
 */

public abstract class BasePresenter<V extends BaseView> {
    private V view;


    public  void attachView(V view){
        this.view=view;
    }

    public void detachView(){
        if (this.view!=null){
            this.view=null;
        }
    }

    public V getView(){
        return this.view;
    }


}
