package com.trendit.api.response;

import com.trendit.api.response.data.BoardData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGetRes extends BaseRes {
    List<BoardData> boardData;

    public static BoardGetRes of(int status, String msg, List<BoardData> boardData) {
        BoardGetRes boardGetRes = new BoardGetRes();
        boardGetRes.setStatusCode(status);
        boardGetRes.setMessage(msg);
        boardGetRes.boardData = boardData;

        return boardGetRes;
    }
}
