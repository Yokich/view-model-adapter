package com.momondo.android.cakemarkapp.viewmodeladapterlibrary;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nwelander on 15/11/2016.
 */

public class SimpleViewModelAdapter<VM extends ViewModelAdapterItem> extends ViewModelAdapter {

    private ArrayList<VM> viewModels;

    public SimpleViewModelAdapter() {
        viewModels = new ArrayList<>();
    }

    public void addViewModel(VM itemViewModel) {
        viewModels.add(itemViewModel);
        notifyItemInserted(viewModels.size()-1);
    }

    public void addViewModels(Collection<VM> viewModels) {
        int startIndex = viewModels.size();
        this.viewModels.addAll(viewModels);
        notifyItemRangeInserted(startIndex, viewModels.size());
    }

    public void setViewModels(Collection<VM> viewModels) {
        int oldSize = this.viewModels.size();
        clear();
        notifyItemRangeRemoved(0, oldSize);

        addViewModels(viewModels);
    }

    @Override
    public ViewModelAdapterItem getItem(int position) {
        return viewModels.get(position);
    }

    @Override
    public int getItemCount() {
        return this.viewModels.size();
    }

    public void clear() {
        int count = viewModels.size();
        this.viewModels.clear();
        notifyItemRangeRemoved(0, count);
    }
}
