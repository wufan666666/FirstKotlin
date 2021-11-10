package com.wf.lifecycle.liveData.simple;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author : wf
 * @date : 2021年11月08日 9:19
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> currentSecond;

    public MutableLiveData<Integer> getCurrentSecond() {
        if (currentSecond == null) {
            currentSecond = new MutableLiveData<>();
            currentSecond.setValue(0);
        }
        return currentSecond;
    }
}