package com.trendit.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.db.entity.Board;
import com.trendit.db.entity.QBoard;
import com.trendit.db.entity.QKeyword;
import com.trendit.db.entity.QNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepositorySupport {

    QBoard qBoard = QBoard.board;
    QKeyword qKeyword = QKeyword.keyword1;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<Board> getFirstBoardsAboutKeyword(long keywordId) {  // 처음 댓글 100개 가져오기
        List<Board> boardsAboutKeyword = new ArrayList<>();
        boardsAboutKeyword = jpaQueryFactory
                .select(qBoard)
                .from(qBoard)
                .limit(100)
                .orderBy(qBoard.boardId.desc())
                .join(qBoard.keyword, qKeyword)
                .where(qBoard.keyword.keywordId.eq(keywordId))
                .fetch();
        return boardsAboutKeyword;
    }

    public List<Board> getBoardsAboutKeyword(long keywordId, long boardId) {  // 스크롤 올리고 이후 댓글 100개 가져오기
        List<Board> boardsAboutKeyword = new ArrayList<>();
        boardsAboutKeyword = jpaQueryFactory
                .select(qBoard)
                .from(qBoard)
                .limit(100)
                .orderBy(qBoard.boardId.desc())
                .join(qBoard.keyword, qKeyword)
                .where((qBoard.keyword.keywordId.eq(keywordId)).and(qBoard.boardId.lt(boardId)))
                .fetch();

        return boardsAboutKeyword;
    }
}
