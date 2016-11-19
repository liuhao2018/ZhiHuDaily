package com.zhihudaily.ui;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.helper.Constant;
import com.zhihudaily.helper.HttpMethods;
import com.zhihudaily.model.StartImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends Activity {
    
    private SimpleDraweeView mSplashView;
    private TextView mNameText;
    private int width = 720;
    private int height = 1080;

    private Handler mHander = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mSplashView = (SimpleDraweeView) findViewById(R.id.iv_splash);
        mNameText = (TextView) findViewById(R.id.tv_image_name);
        loadData();
    }

    private void loadData() {

        HttpMethods.getInstance().getStartImage(new Subscriber<StartImageResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StartImageResponse startImageResponse) {
                if(startImageResponse !=null){
                    mSplashView.setImageURI(startImageResponse.getImg());
                    mNameText.setText(startImageResponse.getText());
                    mHander.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            HomeActivity.showActivity(SplashActivity.this);
                            finish();
                        }
                    }, 1500);
                }
            }
        }, String.format("1920*1080"));
    }
}
