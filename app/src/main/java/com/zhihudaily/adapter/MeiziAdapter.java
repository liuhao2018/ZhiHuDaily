package com.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhihudaily.R;
import com.zhihudaily.model.GankCommonEntity;

import java.util.List;

/**
 * Created by liuhao on 2016/11/6.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziHolder> {

    private List<GankCommonEntity> data;
    private Context context;

    public MeiziAdapter(List<GankCommonEntity> data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public MeiziHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_meizi,null);
        return new MeiziHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziHolder holder, int position) {
        GankCommonEntity item = data.get(position);
        if(item !=null){
            holder.imageView.setImageURI(item.getUrl());
            holder.imageView.getLayoutParams().height =350+(int) (Math.random() * 100);
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

    class MeiziHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView imageView;
        public MeiziHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.iv_meizi);
        }
    }
}
