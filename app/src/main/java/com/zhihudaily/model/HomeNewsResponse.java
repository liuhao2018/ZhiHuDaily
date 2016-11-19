package com.zhihudaily.model;

import java.util.List;

/**
 * Created by liuhao on 2016/10/29.
 */

public class HomeNewsResponse {
    private String date;
    private List<NewsInfo> stories;
    private List<BannerInfo> top_stories;

    public List<BannerInfo> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<BannerInfo> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsInfo> getStories() {
        return stories;
    }

    public void setStories(List<NewsInfo> stories) {
        this.stories = stories;
    }
}
