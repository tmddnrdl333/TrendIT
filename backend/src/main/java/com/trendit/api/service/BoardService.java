package com.trendit.api.service;

import com.trendit.api.request.BoardPostReq;
import com.trendit.db.repository.BoardRepository;
import com.trendit.db.repository.KeywordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final KeywordRepository keywordRepository;

    public void postBoard(BoardPostReq boardPostReq) throws NoSuchElementException {
        // + 비밀번호 암호화
        boardRepository.save(boardPostReq.dtoToEntity(
                keywordRepository.findById(boardPostReq.getKeywordId()).get()
        ));
    }
}
