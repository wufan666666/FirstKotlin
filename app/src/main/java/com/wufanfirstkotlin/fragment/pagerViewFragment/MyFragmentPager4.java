package com.wufanfirstkotlin.fragment.pagerViewFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年04月15日 14:47
 */
public class MyFragmentPager4 extends Fragment {
    private String content;
    public MyFragmentPager4(String content){
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment,container,false);
        TextView num =(TextView) view.findViewById(R.id.num);
        Log.e("tag","onCreateView");
        num.setText(content);
        return view;
    }

}