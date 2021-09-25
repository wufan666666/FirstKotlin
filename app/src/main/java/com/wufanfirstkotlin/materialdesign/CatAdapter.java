package com.wufanfirstkotlin.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.materialdesign.bean.CatBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author : wf
 * @date : 2021年09月23日 15:43
 */
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    private Context context;

    private ArrayList<CatBean> catBeanArrayList = new ArrayList<>();

    public CatAdapter(Context context, ArrayList<CatBean> catBeanArrayList) {
        this.context = context;
        this.catBeanArrayList = catBeanArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview_cat, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CatBean catBean = catBeanArrayList.get(position);
        Glide.with(context).load(catBean.getImageId()).into(holder.catImage);
        holder.textView.setText(catBean.getType());

        holder.catImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra(CatDetailActivity.CAT_NAME,catBean.getType());
                intent.putExtra(CatDetailActivity.CAT_IMAGE_ID,catBean.getImageId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catBeanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView catImage;
        private final TextView textView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            catImage = itemView.findViewById(R.id.cat_image);
            textView = itemView.findViewById(R.id.cat_type);
        }
    }
}