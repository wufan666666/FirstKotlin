package com.wf.lifecycle.databinding.simple;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @author : wf
 * @date : 2021年11月08日 11:40
 */
public class OnEventOnclick {
    private  Context mContext;

    public OnEventOnclick(Context context) {
        mContext = context;
    }

    public void onclick(View view){
        Toast.makeText(mContext,"喜欢",Toast.LENGTH_SHORT).show();
    }
}