package com.zhihudaily.mvp;

/**
 * Created by liuhao on 2016/11/19.
 */

public abstract class ProgressPresenter<V extends IBaseView> extends BasePresenter<V> {
    public abstract void loadData();
}
