package com.zhihudaily.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.adapter.AndroidAdapter;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.model.GankCommonEntity;
import com.zhihudaily.model.GankCommonResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuhao on 2016/11/19.
 */

public class AndroidFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private List<GankCommonEntity> dataList;
    private AndroidAdapter mAdapter;
    private int currentPage = 1;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_jokes,null);
        recyclerView = (XRecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setArrowImageView(R.mipmap.jiantou);
        recyclerView.setLayoutManager(manager);
        recyclerView.setLoadingListener(this);
        dataList = new ArrayList<>();
        mAdapter = new AndroidAdapter(dataList,context);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void loadData() {

        onRefresh();
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        currentPage = 1;
        loadAndroid();
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        loadAndroid();
    }

    private void loadAndroid(){
        MyApplication.getInstance().mRetrofit3.create(ApiService.class)
                .getGankForCategory("Android",10,currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankCommonResponse>() {
                    @Override
                    public void onCompleted() {
                        complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        complete();
                    }

                    @Override
                    public void onNext(GankCommonResponse gankCommonResponse) {
                        if(gankCommonResponse.isError() == false && gankCommonResponse.getResults().size()>0){
                            List<GankCommonEntity> list = gankCommonResponse.getResults();
                            dataList.addAll(list);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void complete(){
        if (currentPage == 1){
            recyclerView.refreshComplete();
        }else{
            recyclerView.loadMoreComplete();
        }
    }
}
