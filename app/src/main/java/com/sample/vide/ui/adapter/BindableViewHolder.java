package com.sample.vide.ui.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindableViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public final T binding;

    public BindableViewHolder(T binding) {
        super(binding.getRoot());

        this.binding = binding;
    }
}