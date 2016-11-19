package com.zhihudaily.model;

import java.util.List;

/**
 * Created by liuhao on 2016/11/6.
 */

public class GankCommonResponse {
    private int count;
    private boolean error;
    private List<GankCommonEntity> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankCommonEntity> getResults() {
        return results;
    }

    public void setResults(List<GankCommonEntity> results) {
        this.results = results;
    }
}
