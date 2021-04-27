package com.wufanfirstkotlin.fragment.pagerViewFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wufanfirstkotlin.R;

import java.util.ArrayList;

/**
 * @author : wf
 * @date : 2021年04月15日 14:47
 */
public class MyFragmentPager1 extends Fragment {
    private String content;
    public MyFragmentPager1(String content){
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment,container,false);
        TextView num =(TextView) view.findViewById(R.id.num);
        ImageView image =(ImageView) view.findViewById(R.id.image);
        Log.e("tag","onCreateView");
        ArrayList<ItemBean> list = new ArrayList();
        for (int i = 0;i<40;i++){
            ItemBean itemBean = new ItemBean();
            itemBean.drawable=getResources().getDrawable(R.mipmap.blue_left);
            itemBean.ibel=" "+i;
            list.add(itemBean);
        }
        Log.e("tag",list.get(0).drawable+"");
        image.setImageDrawable(list.get(0).drawable);
        num.setText(list.get(0).ibel);
        return view;
    }
    class ItemBean{
        public Drawable drawable;
        public String ibel;
    }

}