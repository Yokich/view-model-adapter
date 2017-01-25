package com.momondo.android.cakemarkapp.viewmodeladapterlibrary;

import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.momondo.android.cakemarkapp.BR;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nwelander on 20/09/2016.
 */

public abstract class ViewModelAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int ITEM_VIEW_TYPE_START_INDEX = 1234;
    private Map<String, Integer> identifierStrings = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    private Map<Integer, ViewModelAdapterItem.BindingGenerator> bindingGenerators = new HashMap<>();

    @Override
    public int getItemViewType(int position) {
        ViewModelAdapterItem viewModel = getItem(position);
        String identifierString = viewModel.getIdentifierString();

        if (!identifierStrings.containsKey(identifierString)) {
            int itemViewType = ITEM_VIEW_TYPE_START_INDEX + identifierStrings.size();

            identifierStrings.put(identifierString, itemViewType);
            bindingGenerators.put(itemViewType, viewModel.getBindingGenerator());
        }

        return identifierStrings.get(identifierString);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return bindingGenerators.get(viewType).getViewBindingViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) { // WOULD BE THE SAME; USES POS!
        ViewDataBinding dataBinding = holder.getDataBinding();
        ViewModelAdapterItem item = getItem(position);

        boolean variableDeclared = dataBinding.setVariable(BR.viewmodel, item);

        if (!variableDeclared) {
            throw new RuntimeException("Binding (" + dataBinding.getClass().getSimpleName()
                    + ") does not have the variable \"viewmodel\"! It must have a variable "
                    + "with that exact name ");
        }

        if (item.hasPendingBindings()) {
            dataBinding.notifyChange();
            dataBinding.executePendingBindings();
        }
    }

    public abstract ViewModelAdapterItem getItem(int position);

}
