package com.zhihudaily.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhihudaily.model.BaseItem;

import butterknife.ButterKnife;

/**
 * Created by liuhao on 2016/11/5.
 */

public abstract class BaseViewHolder<T extends BaseItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void onBind(int position, T iItem);
}
