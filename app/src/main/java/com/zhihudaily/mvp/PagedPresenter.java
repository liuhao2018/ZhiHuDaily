package com.zhihudaily.mvp;

/**
 * Created by liuhao on 2016/11/19.
 */

public abstract class PagedPresenter<V extends IBaseView>extends ProgressPresenter<V> {

    protected int currentPage = 1;

    public abstract void loadFirst();

    public abstract void loadNext();
}
