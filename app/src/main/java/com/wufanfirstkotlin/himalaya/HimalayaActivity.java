package com.wufanfirstkotlin.himalaya;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wufanfirstkotlin.R;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HimalayaActivity extends AppCompatActivity {
    private String TAG = "HimalayaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himalaya);
        Map<String, String> map = new HashMap<String, String>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(@Nullable @org.jetbrains.annotations.Nullable CategoryList categoryList) {
                List<Category> categories = categoryList.getCategories();
                if (categories!=null){
                    int size = categories.size();
                    Log.i(TAG,"categories size=====>"+size);
                    for (Category category : categories) {
                        Log.d(TAG,"category =====>" +category.getCategoryName());
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG,"error  message=====>" +s);
            }
        });
    }
}