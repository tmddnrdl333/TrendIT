package com.trendit.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponseBody")
public class BaseRes {
    @ApiModelProperty(name = "응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name = "응답 코드", example = "200")
    Integer statusCode = null;

    public BaseRes() {
    }

    public BaseRes(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public BaseRes(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static BaseRes of(Integer statusCode, String message) {
        BaseRes body = new BaseRes();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}