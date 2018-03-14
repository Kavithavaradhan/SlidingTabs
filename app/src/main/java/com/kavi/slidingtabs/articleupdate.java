package com.kavi.slidingtabs;

/**
 * Created by kavi on 5/3/18.
 */

public class articleupdate {

    String articleId;
    String articleline;

    public articleupdate() {

    }

    public articleupdate(String articleId, String articleline) {
        this.articleId = articleId;
        this.articleline = articleline;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getArticleline() {
        return articleline;
    }
}
