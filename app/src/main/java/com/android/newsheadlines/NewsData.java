package com.android.newsheadlines;

public class NewsData {

    public String title;
    public String webTitle;
    public String authorName;
    public String dataPublished;
    public String articleUrl;

    public NewsData(String title, String webTitle) {
        this.title = title;
        this.webTitle = webTitle;
    }

    public NewsData(String title, String webTitle, String authorName, String dataPublished, String articleUrl) {
        this.title = title;
        this.webTitle = webTitle;
        this.authorName = authorName;
        this.dataPublished = dataPublished;
        this.articleUrl = articleUrl;
    }

    public NewsData(String title, String webTitle, String authorName, String dataPublished) {
        this.title = title;
        this.webTitle = webTitle;
        this.authorName = authorName;
        this.dataPublished = dataPublished;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDataPublished() {
        return dataPublished;
    }
}
