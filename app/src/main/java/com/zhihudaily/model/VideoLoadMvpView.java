package com.zhihudaily.model;

import android.media.MediaPlayer;

import com.waynell.videolist.widget.TextureVideoView;

/**
 * Created by liuhao on 2016/11/5.
 */

public interface VideoLoadMvpView {

    TextureVideoView getVideoView();

    void videoBeginning();

    void videoStopped();

    void videoPrepared(MediaPlayer player);

    void videoResourceReady(String videoPath);
}
