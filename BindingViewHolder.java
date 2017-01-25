package com.momondo.android.cakemarkapp.viewmodeladapterlibrary;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nwelander on 20/09/2016.
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T dataBinding;

    public BindingViewHolder(View itemView) {
        super(itemView);
        dataBinding = DataBindingUtil.bind(itemView);
    }

    public T getDataBinding() {
        return dataBinding;
    }


    /**
     * Created by nwelander on 20/09/2016.
     */
    public interface BindingView {

        ViewDataBinding getBinding();

    }
}
