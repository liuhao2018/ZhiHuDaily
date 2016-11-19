package com.zhihudaily.adapter.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhihudaily.R;
import com.zhihudaily.model.BaseItem;

/**
 * Created by liuhao on 2016/11/5.
 */

public class ViewHolderFactory {
    public static BaseViewHolder<? extends BaseItem> buildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BaseItem.VIEW_TYPE_VIDEO:
                return new VideoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_jiecao_video, parent, false));

            case BaseItem.VIEW_TYPE_PICTURE:
                return new PicViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.pic_list_item, parent, false));

            default:
            case BaseItem.VIEW_TYPE_TEXT:
                return new TextViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.text_list_item, parent, false));

        }
    }

}
