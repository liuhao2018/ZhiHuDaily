package com.zhihudaily.model;

import java.util.List;

/**
 * Created by liuhao on 2016/10/29.
 */

public class ThemeDetailResponse {
    private List<NewsInfo> stories;
    private String description;
    private String background;
    private int color;
    private String name;
    private String image;
    private List<EditorEntity> editors;
    private String image_source;

    public List<NewsInfo> getStories() {
        return stories;
    }

    public void setStories(List<NewsInfo> stories) {
        this.stories = stories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<EditorEntity> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorEntity> editors) {
        this.editors = editors;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
}
