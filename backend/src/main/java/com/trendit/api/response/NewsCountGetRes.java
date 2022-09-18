package com.trendit.api.response;


import com.trendit.api.response.data.NewsCountData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsCountGetRes extends BaseRes {

    NewsCountData data;

    public static NewsCountGetRes of(int statusCode, String message, NewsCountData data) {
        NewsCountGetRes newsCountGetRes = new NewsCountGetRes();
        newsCountGetRes.setStatusCode(statusCode);
        newsCountGetRes.setMessage(message);
        newsCountGetRes.setData(data);
        return newsCountGetRes;
    }
}