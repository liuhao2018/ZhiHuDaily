package com.zhihudaily.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.widget.PullZoomView;
import com.zhihudaily.helper.ApiService;
import com.zhihudaily.MyApplication;
import com.zhihudaily.R;
import com.zhihudaily.helper.Constant;
import com.zhihudaily.helper.HttpMethods;
import com.zhihudaily.model.NewsDetail;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends BaseActivity {

    public static final String NEWS_ID = "news_id";


    public static void showActivity(Context context,int id){
        Intent intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra(NEWS_ID,id);
        context.startActivity(intent);
    }

    private int id;

    private NewsDetail mNewsDetail;

    private SimpleDraweeView mNewsImg;
    private TextView mTitleText;
    private TextView mSourceText;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initView();
        initData();
        loadData();
    }



    private void initView() {

        PullZoomView pzv = (PullZoomView) findViewById(R.id.pzv);
        pzv.setIsParallax(true);
        pzv.setIsZoomEnable(true);
        pzv.setSensitive(3.0f);
        pzv.setZoomTime(500);

        mNewsImg = (SimpleDraweeView) findViewById(R.id.iv_img);
        mTitleText = (TextView) findViewById(R.id.tv_title);
        mSourceText = (TextView) findViewById(R.id.tv_source);
        mWebView = (WebView) findViewById(R.id.webView);
        //提高渲染优先级
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        //把图片放在最后来加载
//        mWebView.getSettings().setBlockNetworkImage(true);
        //不要自动加载图片
        if(Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!view.getSettings().getLoadsImagesAutomatically()) {
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mWebView.getSettings().setDatabaseEnabled(true);
        // 开启Application Cache功能
        mWebView.getSettings().setAppCacheEnabled(true);
    }

    private void initData() {
        Intent data = getIntent();
        if(data !=null){
            id = data.getExtras().getInt(NEWS_ID);
        }
    }

    private void loadData() {

        HttpMethods.getInstance().getNewsDetail(new Subscriber<NewsDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewsDetail newsDetail) {
                if(newsDetail !=null){
                    mNewsDetail = newsDetail;
                    if(mNewsDetail !=null){
                        refreshUI();
                    }
                }
            }
        },id);
    }

    private void refreshUI() {
        mNewsImg.setImageURI(mNewsDetail.getImage());
        mTitleText.setText(mNewsDetail.getTitle());
        mSourceText.setText(String.format("图片:%s",mNewsDetail.getImage_source()));
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + mNewsDetail.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
