package com.wf.lifecycle.viewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.wf.lifecycle.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wf
 * viewModel用于保存瞬态数据。屏幕切换横竖屏的时候
 * 不要向viewModel中传人context，否则会导致内存泄露
 */
public class ViewModelActivity extends AppCompatActivity {

    private TextView mTextView;
    private ViewModelNumber mViewModelNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        mTextView = findViewById(R.id.tv_number);
        mViewModelNumber = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ViewModelNumber.class);
        mTextView.setText(String.valueOf(mViewModelNumber.number));
    }

    public void addNumber(View view) {
        mTextView.setText(String.valueOf(++mViewModelNumber.number));
    }


}