package com.zhihudaily.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.waynell.videolist.visibility.calculator.SingleListViewItemActiveCalculator;
import com.waynell.videolist.visibility.scroll.RecyclerViewItemPositionGetter;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.adapter.VideoAdapter;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.helper.Constant;
import com.zhihudaily.model.JieCaoCommonEntity;
import com.zhihudaily.model.JieCaoResponse;
import com.zhihudaily.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuhao on 2016/11/19.
 */

public class VideoFragment extends BaseFragment implements XRecyclerView.LoadingListener  {

    private XRecyclerView recyclerView;
    private VideoAdapter mAdapter;
    private List<VideoItem> list;
    private int currentPage = 1;
    private int mScrollState;
    private SingleListViewItemActiveCalculator mCalculator;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_video_list,null);
        recyclerView = (XRecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setArrowImageView(R.mipmap.jiantou);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        mAdapter = new VideoAdapter(list,recyclerView);
        mCalculator = new SingleListViewItemActiveCalculator(mAdapter,
                new RecyclerViewItemPositionGetter(manager, recyclerView));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mScrollState = newState;
                if(newState == RecyclerView.SCROLL_STATE_IDLE && mAdapter.getItemCount() > 0){
                    mCalculator.onScrollStateIdle();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mCalculator.onScrolled(mScrollState);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadingListener(this);
        return view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        currentPage = 1;
        loadVideoList();
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        loadVideoList();
    }

    private void loadVideoList() {
        Retrofit retrofit = MyApplication.getInstance().mRetrofit2;
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getListForType(Constant.SHOWAPI_APPID,Constant.SHOWAPI_SIGN,Constant.TYPE_VIDEO,currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JieCaoResponse>() {
                    @Override
                    public void onCompleted() {
                        complete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        complete();
                    }

                    @Override
                    public void onNext(JieCaoResponse jieCaoResponse) {
                        if(jieCaoResponse.getShowapi_res_body() !=null &&
                                jieCaoResponse.getShowapi_res_body().getPagebean().getContentlist().size()>0){
                            for(int i=0;i<jieCaoResponse.getShowapi_res_body().getPagebean().getContentlist().size();i++){
                                JieCaoCommonEntity entity = jieCaoResponse.getShowapi_res_body().getPagebean().getContentlist().get(i);
                                if(entity !=null){
                                    VideoItem item = new VideoItem(entity.getProfile_image()
                                            ,entity.getName(),entity.getCreate_time()
                                            , entity.getText().trim(),entity.getVideo_uri(),"");
                                    list.add(item);
                                }
                            }
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

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
