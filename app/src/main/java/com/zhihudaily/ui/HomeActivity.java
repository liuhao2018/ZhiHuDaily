package com.zhihudaily.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.zhihudaily.adapter.HomePagerAdapter;
import com.zhihudaily.R;
import com.zhihudaily.helper.HttpMethods;
import com.zhihudaily.model.ThemeListRespone;

import rx.Subscriber;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    public static void showActivity(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private HomePagerAdapter mAdapter;
    private String[] titleList = {"日报","妹纸","姿势","视频"};

    private int[] icons = {R.mipmap.xinli,R.mipmap.recommend,R.mipmap.movie,R.mipmap.wuliao
                            ,R.mipmap.desigin,R.mipmap.gongsi,R.mipmap.caijing,R.mipmap.intenet
                            ,R.mipmap.game,R.mipmap.music,R.mipmap.dongman,R.mipmap.sport};
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("悦读");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(),titleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        loadThemeList();
    }

    private void loadThemeList() {

        HttpMethods.getInstance().getThemeList(new Subscriber<ThemeListRespone>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ThemeListRespone themeListRespone) {
                if(themeListRespone !=null && themeListRespone.getOthers().size()>0){
                    for (int i=0;i<themeListRespone.getOthers().size();i++){
                        MenuItem item = menu.add(0,themeListRespone.getOthers().get(i).getId(),i
                                ,themeListRespone.getOthers().get(i).getName());
                        item.setIcon(getResources().getDrawable(icons[i]));
                    }
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(this, ThemeNewsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", item.getTitle());
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
