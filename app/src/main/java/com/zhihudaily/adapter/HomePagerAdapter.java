package com.zhihudaily.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhihudaily.ui.fragment.BaseFragment;
import com.zhihudaily.ui.fragment.AndroidFragment;
import com.zhihudaily.ui.fragment.MeiZhiFragment;
import com.zhihudaily.ui.fragment.VideoFragment;
import com.zhihudaily.ui.fragment.ZHNewsFragment;

/**
 * Created by liuhao on 2016/11/19.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] titleList;

    public HomePagerAdapter(FragmentManager fm,String[] titleList) {
        super(fm);
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = null;
        if(position == 0){
            baseFragment = new ZHNewsFragment();
        }else if(position == 1){
            baseFragment = new MeiZhiFragment();
        }else if(position == 2) {
            baseFragment = new AndroidFragment();
        }else if(position == 3) {
            baseFragment = new VideoFragment();
        }
        return baseFragment;
    }

    @Override
    public int getCount() {
        return titleList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
