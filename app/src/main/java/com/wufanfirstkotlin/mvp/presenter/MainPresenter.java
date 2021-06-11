package com.wufanfirstkotlin.mvp.presenter;

import com.wufanfirstkotlin.mvp.Iview;
import com.wufanfirstkotlin.mvp.model.Callback;
import com.wufanfirstkotlin.mvp.model.IModel;
import com.wufanfirstkotlin.mvp.model.MainModel;
import com.wufanfirstkotlin.mvp.model.User;

/**
 * @author : wf
 * @date : 2021年06月08日 16:32
 */
public class MainPresenter implements Ipresenter, Callback {

    private final IModel mainModel;
    private final Iview mainView;

    public MainPresenter(Iview mainView){
        this.mainModel = new  MainModel();
        this.mainView  = mainView;
    }

    @Override
    public void login(String username, String password) {
        mainView.showProgress();
        mainModel.login(username, password,this);
    }

    @Override
    public void onSuccess(User user) {
        mainView.showProgressSuccess(user);
        mainView.hideProgress();
    }

    @Override
    public void onFailure(String msg) {
        mainView.hideProgress();
        mainView.showProgressFailure(msg);
    }
}