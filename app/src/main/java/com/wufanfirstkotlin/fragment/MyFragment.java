package com.wufanfirstkotlin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.fragment.pagerViewFragment.FragmentPagerActivity;

import org.jetbrains.annotations.NotNull;

/**
 * @author : wf
 * @date : 2021年04月15日 14:47
 */
public class MyFragment extends Fragment {
    private final String content;
    public MyFragment(String content){
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_1,container,false);
        TextView num = view.findViewById(R.id.num);
        Button intent = view.findViewById(R.id.intent_pager_fragment);
        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FragmentPagerActivity.class));
            }
        });
        Log.e("fragment","onCreateView");
        num.setText(content);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fragment","onCreate");
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("fragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("fragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment","onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment","onDetach");
    }

}