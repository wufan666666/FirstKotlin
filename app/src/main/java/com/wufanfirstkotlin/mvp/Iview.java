package com.wufanfirstkotlin.mvp;

import com.wufanfirstkotlin.mvp.model.User;

/**
 * @author : wf
 * @date : 2021年06月08日 16:50
 */
public interface Iview {

    void showProgress();

    void hideProgress();

    void showProgressFailure(String msg);

    void showProgressSuccess(User user);
}

