package com.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhihudaily.R;
import com.zhihudaily.model.EditorEntity;
import com.zhihudaily.model.NewsInfo;
import com.zhihudaily.model.ThemeDetailResponse;
import com.zhihudaily.ui.NewsDetailActivity;

import java.util.List;

/**
 * Created by liuhao on 2016/10/29.
 */

public class ThemeNewsAdapter extends RecyclerView.Adapter<ThemeNewsAdapter.ThemeNewsHolder> {

    private List<NewsInfo> data;

    private Context context;

    public ThemeNewsAdapter(List<NewsInfo> data,Context context){
        this.data = data;
        this.context = context;
    }


    @Override
    public ThemeNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_home_news,null);
        return new ThemeNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(ThemeNewsHolder holder, int position) {
        final NewsInfo item = data.get(position);

        if(item != null){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDetailActivity.showActivity(context,item.getId());
                }
            });
            holder.title.setText(item.getTitle());
            if(item.getImages() !=null && item.getImages().size()>0){
                holder.image.setImageURI(item.getImages().get(0));
            }else{
                holder.image.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    class ThemeNewsHolder extends RecyclerView.ViewHolder{

        ViewGroup container;
        TextView title;
        SimpleDraweeView image;

        public ThemeNewsHolder(View itemView) {
            super(itemView);
            container = (ViewGroup) itemView.findViewById(R.id.container);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            image = (SimpleDraweeView) itemView.findViewById(R.id.iv_img);
        }
    }
}
