package com.trendit.api.response;


import com.trendit.common.model.response.BaseRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsCountGetRes extends BaseRes {
    @AllArgsConstructor
    class NewsCount {
        long todayCount;
        long totalCount;
    }

    NewsCount data;

    public static NewsCountGetRes of(int statusCode, String msg, long todayCount, long totalCount) {
        NewsCountGetRes newsCountGetRes = new NewsCountGetRes();
        newsCountGetRes.setStatusCode(statusCode);
        newsCountGetRes.setMessage(msg);
        newsCountGetRes.setData(newsCountGetRes.new NewsCount(todayCount, totalCount));
        return newsCountGetRes;
    }
}