package com.example.gzp.sunday.View;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gzp.sunday.Base.BaseFragment;
import com.example.gzp.sunday.Contract.AreaContract;
import com.example.gzp.sunday.Presenter.AreaPrestenter;
import com.example.gzp.sunday.R;


import com.example.gzp.sunday.Util.LogUtil;
import com.example.gzp.sunday.View.Adapter.AreaAdapter;


import com.example.gzp.sunday.databinding.ChooseAreaBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_CITY;
import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_COUNTY;
import static com.example.gzp.sunday.View.Adapter.AreaAdapter.LEVEL_PROVINCE;


/**
 * Created by Ben on 2017/12/9.
 */

public class ChooseAreaFragment extends BaseFragment<AreaContract.View,
        AreaContract.Presenter> implements AreaContract.View {

    public static final String TAG = "Fragment";

    private AreaAdapter mAdapter;

    private ProgressDialog mProgressDialog;
    private ChooseAreaBinding mChooseAreaBinding;
   // private AreaItemBinding mAreaItemBinding;



    public List<String> dataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mChooseAreaBinding  = DataBindingUtil.inflate(inflater, R.layout.choose_area, container, false);

        mChooseAreaBinding.areaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false));
        mAdapter=new AreaAdapter(dataList, getActivity(), new AreaAdapter.ItemClickCallback() {
            @Override
            public void queryCities() {
                getPresenter().queryCities();
            }

            @Override
            public void queryCounties() {
                getPresenter().queryCounties();
            }
        });
        LogUtil.v(getClass().toString(),"oncreateView");
        mChooseAreaBinding.areaRecyclerView.setAdapter(mAdapter);

       // mChooseAreaBinding.titleText.setText("中国");
        //mChooseAreaBinding.backButton.setVisibility(View.GONE);
        mChooseAreaBinding.backButton.setOnClickListener(v -> {

           // AreaAdapter adapter= mAdapter;
            //放回上一级
            if ( mAdapter.getLevel()== LEVEL_CITY) {
                LogUtil.v("List","button city");
                mAdapter.setLevel(LEVEL_PROVINCE);
                getPresenter().queryProvinces();
            } else if (mAdapter.getLevel()== LEVEL_COUNTY) {
                LogUtil.v("List","button county");
               mAdapter.setLevel(LEVEL_CITY);
                getPresenter().queryCities();
            }else{
                LogUtil.v("List","button back");
            }
        });



        return mChooseAreaBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().queryProvinces();
    }

    @Override
    protected AreaContract.Presenter createPresenter() {
        LogUtil.v(getClass().toString(),"createPresenter");
        return new AreaPrestenter(mAdapter);
    }


    @Override
    public List<String> getDataList(){
        return dataList;
    }






    @Override
    public void showToast(int Rstring) {
        Toast.makeText(getActivity(), getString(Rstring),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog=new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.isLoading));
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }
    @Override
    public void closeProgressDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
            }
        });

    }

    @Override
    public void refreshItem() {
        mChooseAreaBinding.areaRecyclerView.getAdapter().notifyDataSetChanged();
        mChooseAreaBinding.areaRecyclerView.scrollToPosition(0);
    }

    @Override
    public ChooseAreaBinding getChooseAreaBinding() {
        return this.mChooseAreaBinding;
    }

   /* @Override
    public AreaItemBinding getAreaItemBinding() {
        return this.mAreaItemBinding;
    }*/


    /*public AreaAdapter getAdapter() {
        return mAdapter;
    }*/





}
