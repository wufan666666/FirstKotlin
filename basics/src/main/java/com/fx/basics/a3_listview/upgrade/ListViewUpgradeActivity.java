package com.fx.basics.a3_listview.upgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fx.basics.R;

import java.util.LinkedList;
import java.util.List;

public class ListViewUpgradeActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private Button btn_add;
    private Button btn_add2;
    private Button btn_remove;
    private Button btn_remove2;
    private Button btn_removeAll;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_upgrade);
        mContext = ListViewUpgradeActivity.this;
        bindViews();
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData, mContext);
        list_one.setAdapter(mAdapter);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_add2 = (Button) findViewById(R.id.btn_add2);
        btn_add2.setOnClickListener(this);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(this);
        btn_remove2 = (Button) findViewById(R.id.btn_remove2);
        btn_remove2.setOnClickListener(this);
        btn_removeAll = (Button) findViewById(R.id.btn_removeAll);
        btn_removeAll.setOnClickListener(this);
    }

    private void bindViews() {
        list_one = (ListView) findViewById(R.id.list_one);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mAdapter.add(new Data(R.mipmap.ic_icon_duck, "给鸭哥跪了~~~ x " + flag));
                flag++;
                break;
            case R.id.btn_add2:
                //position从0开始算的
                mAdapter.add(4, new Data(R.mipmap.ic_icon_cow, "给猪哥跪了~~~ x " + flag++));
                break;
            case R.id.btn_remove:
                mAdapter.remove(mData.get(4));
                flag--;
                break;
            case R.id.btn_remove2:
                mAdapter.remove(2);
                break;
            case R.id.btn_removeAll:
                mAdapter.clear();
                break;
        }
    }
}