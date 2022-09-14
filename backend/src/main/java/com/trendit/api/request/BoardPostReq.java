package com.trendit.api.request;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Range;
import com.trendit.db.entity.Board;
import com.trendit.db.entity.Keyword;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class BoardPostReq {
    @ApiParam(value = "키워드 기본키", required = true)
    private long keywordId;

    @ApiParam(value = "사용자 닉네임", required = true)
    @NotBlank
    @Size(max = 15)
    private String userName;

    @ApiParam(value = "패스워드", required = true)
    @NotBlank
    private String password;

    @ApiParam(value = "글 내용", required = true)
    @NotBlank
    private String boardContent;

    public Board dtoToEntity(Keyword keyword) {
        return Board.builder()
                .userName(this.getUserName())
                .password(this.getPassword())
                .boardContent(this.getBoardContent())
                .keyword(keyword)
                .build();
    }
}
