package com.example.gzp.sunday.Base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by BenPhillip on 2018/2/1.
 */

public abstract class BindViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;

    protected BindViewHolder(T binding) {
        super(binding.getRoot());
        this.mBinding=binding;
    }

    protected T getBinding() {
        return this.mBinding;
    }
}
