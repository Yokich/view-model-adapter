package com.momondo.android.cakemarkapp.viewmodeladapterlibrary;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by nwelander on 20/09/2016.
 */

public interface ViewModelAdapterItem {

    /**
     * Getter to give the BindingGenerator to the {@link ViewModelAdapter}
     * @return This implementation would need to create {@link BindingGenerator} and return it here.
     */
    BindingGenerator getBindingGenerator();

    /**
     * unique string for the {@link ViewModelAdapter} to identify the view type
     * @return
     */
    String getIdentifierString();

    /**
     * Override if the view for this viewmodel has bindings that needs notifying and needs to be executed
     * @default false
     *
     * @return true if executePendingBindings and notifyChange should be called when viewmodel is attached to binding
     */
    boolean hasPendingBindings();

    class BindingGenerator {
        @LayoutRes
        private static final int NO_LAYOUT = -300;
        private static final BindingViewHolder.BindingView NO_VIEW = null;

        @LayoutRes
        private int layout = NO_LAYOUT;
        private BindingViewHolder.BindingView view = NO_VIEW;

        public BindingGenerator(@LayoutRes int layout) {
            this.layout = layout;
        }

        BindingGenerator(BindingViewHolder.BindingView view) {
            this.view = view;
        }

        public BindingViewHolder getViewBindingViewHolder(ViewGroup parent) {
            if (view == NO_VIEW && layout != NO_LAYOUT) {
                return new BindingViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false).getRoot());
            } else if (view != NO_VIEW && layout == NO_LAYOUT) {
                return new BindingViewHolder<>(view.getBinding().getRoot());
            } else {
                throw new RuntimeException(this.getClass().getSimpleName() + " must have a valid BindingView or @LayoutRes, and exclusively one of them!");
            }
        }
    }

}
