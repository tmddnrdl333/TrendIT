package com.trendit.api.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class KeywordCompanyPostReq {

    @ApiParam(value = "회사 이름", required = true)
    @NotBlank
    @Size(max = 20)
    private String companyName;

    @ApiParam(value = "회사 카테고리", required = true)
    @Size(max = 50)
    private String companyCategory;

    @ApiParam(value = "회사 대표자 명", required = true)
    @Size(max = 50)
    private String companyRepresentative;

    @ApiParam(value = "회사 링크", required = true)
    @Size(max = 500)
    private String companyLink;

}
