package com.trendit.api.response;

import com.trendit.api.response.data.KeywordData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordGetRes extends BaseRes {
    KeywordData data;

    public static KeywordGetRes of(int statusCode, String message, KeywordData data) {
        KeywordGetRes keywordGetRes = new KeywordGetRes();
        keywordGetRes.setStatusCode(statusCode);
        keywordGetRes.setMessage(message);
        keywordGetRes.setData(data);

        return keywordGetRes;
    }
}
