package com.zhihudaily.mvp;

/**
 * Created by liuhao on 2016/11/19.
 */

public interface IPagedView<T> extends IProgressView<T> {
    void addHeaderView(T t);

    //显示全部加载完成指示
    void showAllLoadedIndicator();

    void refreshComplete();

    void clearView();
}
