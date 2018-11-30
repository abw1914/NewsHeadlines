package com.android.newsheadlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {
    private static final String LOG_TAG = NewsLoader.class.getSimpleName();

    private String mUrl;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsData> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        List<NewsData> newsDataList = (List<NewsData>) Utils.getNewsData(mUrl);
        return newsDataList;
    }
}
