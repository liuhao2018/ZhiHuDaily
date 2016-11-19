package com.zhihudaily.model;

/**
 * Created by liuhao on 2016/11/5.
 */

public class VideoItem extends BaseItem {
    private String mVideoUrl;
    private String mCoverUrl;
    private String mAuthorName;
    private String mVideoDesc;
    private String mVideoTime;
    private String mAuthorAvatar;

    public VideoItem(String avatar,String author_name,String time,String desc,String videoUrl, String coverUrl) {
        super(BaseItem.VIEW_TYPE_VIDEO);
        mAuthorAvatar = avatar;
        mAuthorName = author_name;
        mVideoTime = time;
        mVideoDesc = desc;
        mVideoUrl = videoUrl;
        mCoverUrl = coverUrl;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public String getmCoverUrl() {
        return mCoverUrl;
    }

    public void setmCoverUrl(String mCoverUrl) {
        this.mCoverUrl = mCoverUrl;
    }

    public String getmAuthorName() {
        return mAuthorName;
    }

    public void setmAuthorName(String mAuthorName) {
        this.mAuthorName =mAuthorName;
    }

    public String getmVideoDesc() {
        return mVideoDesc;
    }

    public void setmVideoDesc(String mVideoDesc) {
        this.mVideoDesc = mVideoDesc;
    }

    public String getmVideoTime() {
        return mVideoTime;
    }

    public void setmVideoTime(String mVideoTime) {
        this.mVideoTime = mVideoTime;
    }

    public String getmAuthorAvatar() {
        return mAuthorAvatar;
    }

    public void setmAuthorAvatar(String mAuthorAvatar) {
        this.mAuthorAvatar = mAuthorAvatar;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }
}
