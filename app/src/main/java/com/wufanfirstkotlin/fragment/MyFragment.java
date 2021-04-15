package com.wufanfirstkotlin.fragment;

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
public class MyFragment extends Fragment {
    private String content;
    public MyFragment(String content){
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("tag","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tag","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("tag","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("tag","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("tag","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("tag","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("tag","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("tag","onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("tag","onDetach");
    }

}