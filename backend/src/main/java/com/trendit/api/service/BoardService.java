package com.trendit.api.service;

import com.trendit.api.request.BoardPostReq;
import com.trendit.common.util.SHA256;
import com.trendit.db.repository.BoardRepository;
import com.trendit.db.repository.KeywordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final KeywordRepository keywordRepository;

    public void postBoard(BoardPostReq boardPostReq) throws NoSuchElementException, NoSuchAlgorithmException {
        // + 비밀번호 암호화
        boardPostReq.setPassword(new SHA256().getHash(boardPostReq.getPassword()));
        boardRepository.save(boardPostReq.dtoToEntity(
                keywordRepository.findById(boardPostReq.getKeywordId()).get()
        ));
    }
}
