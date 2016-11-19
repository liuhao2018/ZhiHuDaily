package com.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhihudaily.R;
import com.zhihudaily.model.GankCommonEntity;
import com.zhihudaily.ui.WebActivity;

import java.util.List;

/**
 * Created by liuhao on 2016/11/5.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidHolder>{

    private List<GankCommonEntity> data;
    private Context context;

    public AndroidAdapter(List<GankCommonEntity> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public AndroidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        return new AndroidHolder(v);
    }

    @Override
    public void onBindViewHolder(AndroidHolder holder, int position) {
        final GankCommonEntity item = data.get(position);
        if(item != null){
            if(item.getImages() !=null && item.getImages().size()>0){
                holder.avatar.setImageURI(item.getImages().get(0));
            }
            holder.content.setText(item.getDesc());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   context.startActivity( WebActivity.newIntent(context,item.getUrl(),item.getDesc()));
                }
            });
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

    class AndroidHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView avatar;
        TextView content;
        public AndroidHolder(View itemView) {
            super(itemView);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.iv_img);
            content = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
