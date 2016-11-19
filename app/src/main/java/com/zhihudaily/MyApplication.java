package com.zhihudaily;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhihudaily.helper.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liuhao on 2016/10/29.
 */

public class MyApplication extends Application {

    private static MyApplication mApp;

    public static Retrofit mRetrofit2 = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL2)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public static Retrofit mRetrofit3 = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL3)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Fresco.initialize(this);
    }

    public static MyApplication getInstance(){
        return mApp;
    }

}
