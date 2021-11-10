package com.wf.lifecycle.liveData.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author : wf
 * @date : 2021年11月08日 10:23
 */
public class MyFragmentViewModel extends ViewModel {
        private MutableLiveData<Integer> process;

    public MutableLiveData<Integer> getProcess() {
        if (process == null){
            process = new MutableLiveData<>();
            process.setValue(0);
        }
        return process;
    }
}