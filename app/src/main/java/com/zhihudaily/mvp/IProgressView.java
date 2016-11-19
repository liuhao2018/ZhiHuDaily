package com.zhihudaily.mvp;

/**
 * Created by liuhao on 2016/11/19.
 */

public interface IProgressView<T> extends IBaseView {
    void showProgress();

    void hideProgress();

    void showContentView(T t);

    void showContentContainer();

    void showErrorView();

    void showErrorView401();

    void showEmptyView();
}
