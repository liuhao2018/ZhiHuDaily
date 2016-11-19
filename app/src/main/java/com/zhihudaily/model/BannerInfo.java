package com.zhihudaily.model;

/**
 * Created by liuhao on 2016/10/29.
 */

public class BannerInfo {
    private String image;
    private int type;
    private int id;
    private int ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(int ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
