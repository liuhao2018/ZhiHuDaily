package com.zhihudaily.model;

import java.util.List;

/**
 * Created by liuhao on 2016/10/29.
 */

public class ThemeListRespone {
    private int limit;
    private List<ThemeEntity> subscribed;
    private List<ThemeEntity> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<ThemeEntity> getOthers() {
        return others;
    }

    public void setOthers(List<ThemeEntity> others) {
        this.others = others;
    }

    public List<ThemeEntity> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<ThemeEntity> subscribed) {
        this.subscribed = subscribed;
    }
}
