package com.trendit.api.response;

import com.trendit.api.response.data.BoardData;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostRes extends BaseRes {

    BoardData data;

    public static BoardPostRes of(int status, String msg, Board board) {
        BoardPostRes boardGetRes = new BoardPostRes();
        boardGetRes.setStatusCode(status);
        boardGetRes.setMessage(msg);
        boardGetRes.data = board.entityToDto();

        return boardGetRes;
    }
}
