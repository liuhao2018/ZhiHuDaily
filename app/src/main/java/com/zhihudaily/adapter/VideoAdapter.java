package com.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.waynell.videolist.visibility.items.ListItem;
import com.waynell.videolist.visibility.scroll.ItemsProvider;
import com.zhihudaily.adapter.holder.BaseViewHolder;
import com.zhihudaily.adapter.holder.ViewHolderFactory;
import com.zhihudaily.model.BaseItem;

import java.util.List;


/**
 * Created by liuhao on 2016/11/5.
 */
public class VideoAdapter extends RecyclerView.Adapter<BaseViewHolder<? extends BaseItem>>
        implements ItemsProvider {

    private List<? extends BaseItem> mListItems;
    private RecyclerView recyclerView;

    public VideoAdapter(List<? extends BaseItem> data, RecyclerView recyclerView) {
        this.mListItems = data;
        this.recyclerView = recyclerView;
    }

    @Override
    public BaseViewHolder<? extends BaseItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderFactory.buildViewHolder(parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        BaseItem baseItem = mListItems.get(position);
        holder.onBind(position, baseItem);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mListItems.get(position).getViewType();
    }

    @Override
    public ListItem getListItem(int position) {
        RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(position);
        if (holder instanceof ListItem) {
            return (ListItem) holder;
        }
        return null;
    }

    public void clear(){
        mListItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int listItemSize() {
        return getItemCount();
    }
}
