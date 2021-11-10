package com.wf.lifecycle.databinding.editView;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wf.lifecycle.BR;

/**
 * @author : wf
 * @date : 2021年11月08日 16:06
 */
public class EditViewModel extends BaseObservable {
    private User mUser;

    public EditViewModel() {
        this.mUser = new User("jack");
    }

    @Bindable
    public String getUserName() {
        return mUser.userName;
    }

    public void setUserName(String userName) {
        if (!TextUtils.isEmpty(userName) && !mUser.userName.equals(userName)) {
            mUser.userName = userName;
            Log.e("wufan","username has set with "+ userName);
            notifyPropertyChanged(BR.userName);
        }
    }
}