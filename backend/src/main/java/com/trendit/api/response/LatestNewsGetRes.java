package com.trendit.api.response;

import com.trendit.api.response.data.NewsData;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.News;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LatestNewsGetRes extends BaseRes {

    List<NewsData> data;

    public static LatestNewsGetRes of(int statusCode, String msg, List<News> data) {
        LatestNewsGetRes latestNewsGetRes = new LatestNewsGetRes();
        latestNewsGetRes.setStatusCode(statusCode);
        latestNewsGetRes.setMessage(msg);
        latestNewsGetRes.setData(new ArrayList<>());
        for (News news : data) {
            latestNewsGetRes.getData()
                    .add(NewsData.builder()
                            .newsId(news.getNewsId())
                            .headline(news.getHeadline())
                            .newsContent(news.getNewsContent())
                            .newsDate(news.getNewsDate())
                            .newsAgency(news.getNewsAgency())
                            .newsLink(news.getNewsLink())
                            .imgLink(news.getImgLink())
                            .build()
                    );
        }
        return latestNewsGetRes;
    }
}