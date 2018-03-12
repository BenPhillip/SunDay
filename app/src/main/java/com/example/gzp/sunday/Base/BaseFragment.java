package com.example.gzp.sunday.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * {onAttach(Context) onCreate(Bundle) onCreatView }=> setContentView()
 * onActivityCreated(Bundle)
 * onStart() onResume() onPause onStop
 * onDestroyView()
 * onDetach() onDestroy()
 */

public abstract class BaseFragment <V extends BaseView ,P extends BasePresenter<V>>
        extends Fragment implements BaseView  {

    private P presenter;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter=createPresenter();
        }

        presenter.attachView((V) this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }




    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }

}
