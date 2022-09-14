package com.trendit.api.service;

import com.trendit.api.exception.PasswordMisMatchException;
import com.trendit.api.request.BoardPostReq;
import com.trendit.api.request.BoardUpdateReq;
import com.trendit.common.util.SHA256;
import com.trendit.db.entity.Board;
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
        System.out.println(new SHA256().getHash(boardPostReq.getPassword()));
        boardPostReq.setPassword(new SHA256().getHash(boardPostReq.getPassword()));
        boardRepository.save(boardPostReq.dtoToEntity(
                keywordRepository.findById(boardPostReq.getKeywordId()).get()
        ));
    }

    public void updateBoard(BoardUpdateReq boardUpdateReq) throws NoSuchElementException, NoSuchAlgorithmException, PasswordMisMatchException {
        Board board = boardRepository.findById(boardUpdateReq.getBoardId()).get();
        if (checkPassword(board, boardUpdateReq.getPassword())) {
            board.setBoardContent(boardUpdateReq.getBoardContent());
            boardRepository.save(board);
        } else {
            throw new PasswordMisMatchException("암호가 틀렸습니다");
        }
    }

    public void deleteBoard(long boardId, String password) throws NoSuchAlgorithmException, NoSuchElementException, PasswordMisMatchException {
        Board board = boardRepository.findById(boardId).get();
        if (checkPassword(board, password)) {
            boardRepository.delete(board);
        } else {
            throw new PasswordMisMatchException("암호가 틀렸습니다");
        }
    }

    private boolean checkPassword(Board board, String password) throws NoSuchAlgorithmException {
        if (board.getPassword().equals(new SHA256().getHash(password))) {
            return true;
        }
        return false;
    }
}
