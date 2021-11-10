package com.wf.lifecycle.databinding.recycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ItemBinding;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月09日 10:31
 */
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.MyViewHolder> {

    private List<Cat> mCats;

    public CatAdapter(List<Cat> cats) {
        mCats = cats;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item,
                parent,
                false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(CatAdapter.MyViewHolder holder, int position) {
        Cat cat = mCats.get(position);
        holder.itemBinding.setCat(cat);
    }

    @Override
    public int getItemCount() {
        return mCats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private  ItemBinding itemBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public MyViewHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}