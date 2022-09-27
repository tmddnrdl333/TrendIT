package com.trendit.api.request;

import com.trendit.db.entity.Board;
import com.trendit.db.entity.Company;
import com.trendit.db.entity.Keyword;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class KeywordPostReq {

    @ApiParam(value = "키워드", required = true)
    @NotBlank
    @Size(max = 20)
    private String keyword;

}
