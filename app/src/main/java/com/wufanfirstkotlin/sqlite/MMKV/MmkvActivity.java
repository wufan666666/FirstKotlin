package com.wufanfirstkotlin.sqlite.MMKV;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tencent.mmkv.MMKV;
import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.utils.L;

public class MmkvActivity extends BaseActivity {


    private MMKV mMmkv;
    private EditText mmkvEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        String relativePath = getFilesDir().getAbsolutePath() + "/mmkv_3";
        System.out.println(relativePath);
        mMmkv = MMKV.mmkvWithID("testCustomDir", relativePath);
        L.e("testCustomDir", relativePath);
        initView();
    }

    public void saveMmkv(View view) {
        mMmkv.encode("string", getPackageName());
        mMmkv.encode("int", Integer.MAX_VALUE);
        mMmkv.encode("float", Float.MAX_VALUE);
        mMmkv.encode("long", Long.MAX_VALUE);
        mMmkv.encode("double", Double.MAX_VALUE);
        byte[] bytes = {'m', 'm', 'k', 'v'};
        mMmkv.encode("bytes", bytes);
        toast("成功存储数据到/data/user/0/com.wufanfirstkotlin/files/mmkv_3");
    }

    public void getMmkv(View view) {
        String s1 = mMmkv.decodeString("string");
        Integer s2 = mMmkv.decodeInt("int");
        Float s3 = mMmkv.decodeFloat("float");
        Long s4 = mMmkv.decodeLong("long");
        Double s5 = mMmkv.decodeDouble("double");
        byte[] s6 = mMmkv.decodeBytes("bytes");
        toast((s1==null?"":s1) + "\n" + (s2==null?"":s2) + "\n" + (s3==null?"":s3) + "\n" +
                (s4==null?"":s4) + "\n" + (s5==null?"":s5) + "\n" + (s6==null?"":new String(s6)));
        char c = s1.charAt(1);


    }

    public void deleteMmkv(View view) {
        if ("all".equals(mmkvEdit.getText().toString())) {
            mMmkv.clearAll();
            toast("已删除全部的内容");
        } else {
            //mMmkv.removeValueForKey(mmkvEdit.getText().toString()); 只删除value
            mMmkv.remove(mmkvEdit.getText().toString());
            toast("已删除" + mmkvEdit.getText().toString() + "的内容");
        }
    }

    private void initView() {
        mmkvEdit = (EditText) findViewById(R.id.mmkv_edit);

    }
}