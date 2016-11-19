package com.zhihudaily.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.adapter.ThemeNewsAdapter;
import com.zhihudaily.helper.Constant;
import com.zhihudaily.helper.HttpMethods;
import com.zhihudaily.model.NewsInfo;
import com.zhihudaily.model.ThemeDetailResponse;
import com.zhihudaily.mvp.IPagedView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ThemeNewsActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    private int themeId;
    private TextView mNameText;
    private XRecyclerView mRecyclerView;
    private List<NewsInfo> list  = new ArrayList<>();
    private ThemeNewsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_news);
        init();
        mRecyclerView.setRefreshing(true);
    }


    private void init() {

        mNameText = (TextView) findViewById(R.id.tv_title);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setArrowImageView(R.mipmap.jiantou);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setLoadingMoreEnabled(false);

        Intent data = getIntent();
        if(data !=null){
            themeId = data.getExtras().getInt("id");
            String name = data.getExtras().getString("name");
            mNameText.setText(name);
        }
        mAdapter = new ThemeNewsAdapter(list,this);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void loadData() {

        HttpMethods.getInstance().getThemeDetail(new Subscriber<ThemeDetailResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ThemeDetailResponse themeDetailResponse) {
                if(themeDetailResponse !=null && themeDetailResponse.getStories().size()>0){
                    mRecyclerView.refreshComplete();
                    List<NewsInfo> newsInfo = themeDetailResponse.getStories();
                    list.addAll(newsInfo);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.setIsnomore(true);
                }
            }
        },themeId);
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        mRecyclerView.setIsnomore(false);
        loadData();
    }

    @Override
    public void onLoadMore() {

    }
}
