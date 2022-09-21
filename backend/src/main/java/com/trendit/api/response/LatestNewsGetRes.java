package com.trendit.api.response;

import com.trendit.api.response.data.NewsData;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.News;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Getter
@Setter
public class LatestNewsGetRes extends BaseRes {

    Page<NewsData> data;

    public static LatestNewsGetRes of(int statusCode, String msg, Page<News> newsPage) {
        LatestNewsGetRes latestNewsGetRes = new LatestNewsGetRes();
        latestNewsGetRes.setStatusCode(statusCode);
        latestNewsGetRes.setMessage(msg);

        latestNewsGetRes.setData(
                newsPage.map(m -> NewsData
                        .builder()
                        .newsId(m.getNewsId())
                        .headline(m.getHeadline())
                        .imgLink(m.getImgLink())
                        .newsAgency(m.getNewsAgency())
                        .newsContent(m.getNewsContent())
                        .newsDate(m.getNewsDate())
                        .newsLink(m.getNewsLink())
                        .build())
        );

        return latestNewsGetRes;
    }
}