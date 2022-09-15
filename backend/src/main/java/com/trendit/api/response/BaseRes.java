package com.trendit.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRes {

    String message;

    int statusCode;

    public static BaseRes of(int statusCode, String message) {
        BaseRes body = new BaseRes();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
