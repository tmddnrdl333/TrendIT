package com.trendit.api.request;

import lombok.Getter;

@Getter
public class BoardUpdateReq {
    long boardId;
    String password;
    String boardContent;
}
