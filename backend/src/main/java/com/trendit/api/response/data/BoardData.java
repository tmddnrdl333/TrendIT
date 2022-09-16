package com.trendit.api.response.data;

import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@ResponseBody
@ToString
@Getter
@Setter
public class BoardData {

    long boardId;
    String userName;
    String boardContent;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    /* TODO: noargsCon~로 바꾸기? */
    public BoardData() {
        super();
    }


    public static BoardData of(long boardId,
                               String userName,
                               String boardContent,
                               LocalDateTime createdDate,
                               LocalDateTime updatedDate) {
        BoardData boardData = new BoardData();
        boardData.boardId = boardId;
        boardData.userName = userName;
        boardData.boardContent = boardContent;
        boardData.createdDate = createdDate;
        boardData.updatedDate = updatedDate;

        return boardData;
    }
}
