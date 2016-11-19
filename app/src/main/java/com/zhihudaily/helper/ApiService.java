package com.zhihudaily.helper;


import android.support.annotation.Dimension;

import com.zhihudaily.model.GankCommonResponse;
import com.zhihudaily.model.HomeNewsResponse;
import com.zhihudaily.model.JieCaoResponse;
import com.zhihudaily.model.NewsDetail;
import com.zhihudaily.model.StartImageResponse;
import com.zhihudaily.model.ThemeDetailResponse;
import com.zhihudaily.model.ThemeListRespone;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by liuhao on 2016/10/29.
 */

public interface ApiService {

    //知乎日报
    @GET("news/latest")
    Observable<HomeNewsResponse> getHomeNews();
    @GET("news/{id}")
    Observable<NewsDetail> getNewsDetail(@Path("id") int id);
    @GET("themes")
    Observable<ThemeListRespone> getThemeList();
    @GET("theme/{id}")
    Observable<ThemeDetailResponse> getThemeDetail(@Path("id") int id);
    @GET("start-image/{dm}")
    Observable<StartImageResponse> getStartImage(@Path("dm") String dm);

    //百思不得姐
    @GET("255-1")
    Observable<JieCaoResponse> getListForType
        (@Query("showapi_appid")String app_id,@Query("showapi_sign")String sign,@Query("type") int type,@Query("page") int page);

    @GET("{type}/{count}/{page}")
    Observable<GankCommonResponse> getGankForCategory(@Path ("type")String type,@Path("count") int count,@Path("page") int page);


}
