package com.trendit.api.response;

import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeywordNewsGetRes extends BaseRes {

    List<KeywordNewsData> data;

    public static KeywordNewsGetRes of(int statusCode, String message, List<KeywordNewsData> data) {
        KeywordNewsGetRes keywordNewsGetRes = new KeywordNewsGetRes();
        keywordNewsGetRes.setStatusCode(statusCode);
        keywordNewsGetRes.setMessage(message);
        keywordNewsGetRes.setData(data);
        return keywordNewsGetRes;
    }

}
