package com.android.newsheadlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsData> {
    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NewsData> newsDataArrayList) {
        super(context, 0, newsDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsDataArrayList = convertView;

        if(newsDataArrayList == null) {
            newsDataArrayList = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }
        NewsData newsData = getItem(position);

        TextView title = newsDataArrayList.findViewById(R.id.article_title);
        title.setText(newsData.title);

        TextView author = newsDataArrayList.findViewById(R.id.article_author);
        title.setText(newsData.authorName);

        TextView date = newsDataArrayList.findViewById(R.id.article_date);
        title.setText(newsData.dataPublished);

        return newsDataArrayList;
    }
}
