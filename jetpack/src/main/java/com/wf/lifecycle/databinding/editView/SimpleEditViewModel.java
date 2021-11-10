package com.wf.lifecycle.databinding.editView;

import android.util.Log;

import androidx.databinding.ObservableField;

/**
 * @author : wf
 * @date : 2021年11月08日 16:31
 */
public class SimpleEditViewModel {
    private ObservableField<User> mUserObservableField;

    public SimpleEditViewModel() {
        if (mUserObservableField==null) {
            mUserObservableField = new ObservableField<>();
            User user =new User("jack");
            mUserObservableField.set(user);
        }
    }
    public String getUserName(){
        return mUserObservableField.get().userName;
    }

    public void setUserName(String userName){
        Log.e("SimpleEditViewModel","username has set with "+ userName);
        mUserObservableField.get().userName = userName;
    }
}