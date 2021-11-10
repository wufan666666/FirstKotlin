package com.wf.lifecycle.liveData.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.wf.lifecycle.R;

public class SecondFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_second, container, false);
        SeekBar seekBar = inflate.findViewById(R.id.seekBar);

        MyFragmentViewModel myFragmentViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MyFragmentViewModel.class);
        myFragmentViewModel.getProcess().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                seekBar.setProgress(integer);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myFragmentViewModel.getProcess().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return inflate;
    }

}