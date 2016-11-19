package com.zhihudaily.model;

/**
 * Created by liuhao on 2016/11/5.
 */

public class TextItem extends BaseItem {

    private String mText;

    public TextItem(String text) {
        super(BaseItem.VIEW_TYPE_TEXT);
        mText = text;
    }

    public String getText() {
        return mText;
    }
}
