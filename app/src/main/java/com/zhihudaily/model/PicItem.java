package com.zhihudaily.model;

/**
 * Created by liuhao on 2016/11/5.
 */

public class PicItem extends BaseItem {

    private String mCoverUrl;

    public PicItem(String coverUrl) {
        super(BaseItem.VIEW_TYPE_PICTURE);
        mCoverUrl = coverUrl;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }
}
