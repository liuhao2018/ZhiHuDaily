package com.zhihudaily.mvp;

/**
 * Created by liuhao on 2016/11/19.
 */

public class BasePresenter<V extends IBaseView>  {

    protected V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }
}
