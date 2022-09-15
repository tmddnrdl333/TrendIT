package com.trendit.api.response;

import com.trendit.api.response.data.BoardData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGetRes extends BaseRes {
    //    BaseRes baseRes;
    List<BoardData> boardData;

    public static BoardGetRes of(int status, String msg, List<BoardData> boardData) {
        BoardGetRes boardGetRes = new BoardGetRes();
//        boardGetRes.baseRes = baseRes;
        boardGetRes.statusCode = status;
        boardGetRes.message = msg;
        boardGetRes.boardData = boardData;
        return boardGetRes;
    }
}
