package com.trendit.api.response;

import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LatestNewsGetRes extends BaseRes {

    List<News> data;

    public static LatestNewsGetRes of(int statusCode, String msg, List data) {
        LatestNewsGetRes latestNewsGetRes = new LatestNewsGetRes();
        latestNewsGetRes.setStatusCode(statusCode);
        latestNewsGetRes.setMessage(msg);
        latestNewsGetRes.setData(data);
        return latestNewsGetRes;
    }
}