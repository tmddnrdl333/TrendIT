package com.trendit.api.response;


import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsGetRes extends BaseRes {

    List<News> news;

    public static NewsGetRes of(int status, String msg, List<News> news) {
        NewsGetRes newsGetRes = new NewsGetRes();
        newsGetRes.setStatusCode(status);
        newsGetRes.setMessage(msg);
        newsGetRes.news = news;

        return newsGetRes;
    }
}
