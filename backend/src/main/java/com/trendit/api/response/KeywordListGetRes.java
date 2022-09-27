package com.trendit.api.response;

import com.trendit.api.response.data.KeywordData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeywordListGetRes extends BaseRes {

    List<KeywordData> data;

    public static KeywordListGetRes of(int statusCode, String message, List<KeywordData> data) {
        KeywordListGetRes keywordListGetRes = new KeywordListGetRes();
        keywordListGetRes.setStatusCode(statusCode);
        keywordListGetRes.setMessage(message);
        keywordListGetRes.setData(data);

        return keywordListGetRes;
    }
}
