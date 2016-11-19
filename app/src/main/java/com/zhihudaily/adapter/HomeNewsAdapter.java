package com.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhihudaily.R;
import com.zhihudaily.model.NewsInfo;
import com.zhihudaily.ui.NewsDetailActivity;

import java.util.List;

/**
 * Created by liuhao on 2016/10/29.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsHolder> {

    private List<NewsInfo> newsInfoList;
    private Context context;

    public HomeNewsAdapter(List<NewsInfo> newsInfoList,Context context){
        this.newsInfoList = newsInfoList;
        this.context = context;
    }

    @Override
    public HomeNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_home_news,null);
        return new HomeNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeNewsHolder holder, int position) {
        final NewsInfo item = newsInfoList.get(position);
        if(item != null){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDetailActivity.showActivity(context,item.getId());
                }
            });
            holder.title.setText(item.getTitle());
            holder.image.setImageURI(item.getImages().get(0));
        }
    }

    @Override
    public int getItemCount() {
        return newsInfoList.size();
    }

    public void clear(){
        newsInfoList.clear();
        notifyDataSetChanged();
    }

    class HomeNewsHolder extends RecyclerView.ViewHolder{

        ViewGroup container;
        TextView title;
        SimpleDraweeView image;

        public HomeNewsHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView.findViewById(R.id.container);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            image = (SimpleDraweeView) itemView.findViewById(R.id.iv_img);
        }
    }
}
