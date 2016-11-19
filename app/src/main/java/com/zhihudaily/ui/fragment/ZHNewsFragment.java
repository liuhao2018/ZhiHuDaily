package com.zhihudaily.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.zhihudaily.R;
import com.zhihudaily.adapter.HomeNewsAdapter;
import com.zhihudaily.helper.HttpMethods;
import com.zhihudaily.model.BannerInfo;
import com.zhihudaily.model.HomeNewsResponse;
import com.zhihudaily.model.NewsInfo;
import com.zhihudaily.model.ThemeListRespone;
import com.zhihudaily.ui.HomeActivity;
import com.zhihudaily.ui.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by liuhao on 2016/11/19.
 */

public class ZHNewsFragment extends BaseFragment implements XRecyclerView.LoadingListener{

    private XRecyclerView mRecyclerView;
    private XBanner mBannerView;
    private HomeNewsAdapter mAdapter;
    private List<NewsInfo> mNewsList;
    private List<BannerInfo> mBannerList;
    @Override
    public View initView() {


        View view = View.inflate(context,R.layout.zh_news_list,null);
        mRecyclerView = (XRecyclerView)view. findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setArrowImageView(R.mipmap.jiantou);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setLoadingMoreEnabled(false);
        mNewsList = new ArrayList<>();
        mAdapter = new HomeNewsAdapter(mNewsList,context);
        mRecyclerView.setAdapter(mAdapter);

        mBannerList = new ArrayList<>();
        View headerView = View.inflate(context,R.layout.home_news_top,null);
        mBannerView = (XBanner) headerView.findViewById(R.id.banner);
        mRecyclerView.addHeaderView(headerView);
        return view;
    }

    @Override
    public void loadData() {
        onRefresh();
    }

    private void loadNews(){
        HttpMethods.getInstance().getLatesNews(new Subscriber<HomeNewsResponse>() {
            @Override
            public void onCompleted() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onError(Throwable e) {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onNext(HomeNewsResponse homeNewsResponse) {
                if (homeNewsResponse != null) {

                    //列表
                    List<NewsInfo> newsData = homeNewsResponse.getStories();
                    mNewsList.addAll(newsData);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.setIsnomore(true);

                    //轮播图
                    List<BannerInfo> bannerData = homeNewsResponse.getTop_stories();
                    final List<String> img = new ArrayList<>();
                    List<String> text = new ArrayList<>();
                    for (int i = 0; i < bannerData.size(); i++) {
                        String url = bannerData.get(i).getImage();
                        String title = bannerData.get(i).getTitle();
                        img.add(url);
                        text.add(title);
                    }
                    if (mBannerList.size() == 0) {
                        mBannerList.addAll(bannerData);
                        mBannerView.setData(img, text);
                        mBannerView.setmAdapter(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, View view, int position) {
                                Glide.with(context).load(img.get(position)).fitCenter().into((ImageView) view);
                            }
                        });
                        mBannerView.setOnItemClickListener(new XBanner.OnItemClickListener() {
                            @Override
                            public void onItemClick(XBanner banner, int position) {
                                NewsDetailActivity.showActivity(context, mBannerList.get(position).getId());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        mRecyclerView.setIsnomore(false);
        loadNews();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerView.startAutoPlay();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBannerView.stopAutoPlay();
    }
}
