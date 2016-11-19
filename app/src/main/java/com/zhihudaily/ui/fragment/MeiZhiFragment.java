package com.zhihudaily.ui.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.adapter.MeiziAdapter;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.helper.SpacesItemDecoration;
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

public class MeiZhiFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    private XRecyclerView recyclerView;
    private int currentPage = 1;
    private MeiziAdapter mAdapter;
    private List<GankCommonEntity> dataList = new ArrayList<>();

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_meizi,null);
        recyclerView = (XRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setArrowImageView(R.mipmap.jiantou);
        recyclerView.setLoadingMoreEnabled(true);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        SpacesItemDecoration decoration=new SpacesItemDecoration(32);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLoadingListener(this);
        mAdapter = new MeiziAdapter(dataList,context);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setRefreshing(true);
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
        loadMeizi();
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        loadMeizi();
    }

    private void loadMeizi(){
        MyApplication.getInstance().mRetrofit3.create(ApiService.class)
                .getGankForCategory("福利",50,currentPage)
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
