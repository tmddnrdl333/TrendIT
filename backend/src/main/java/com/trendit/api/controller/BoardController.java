package com.trendit.api.controller;

import com.trendit.api.exception.PasswordMisMatchException;
import com.trendit.api.request.BoardPostReq;
import com.trendit.api.request.BoardUpdateReq;
import com.trendit.api.response.BaseRes;
import com.trendit.api.response.BoardGetRes;
import com.trendit.api.response.data.BoardData;
import com.trendit.api.service.BoardService;
import com.trendit.db.entity.Board;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/board")
@AllArgsConstructor  // 생성자 주입 방식 <> autowired 필드 주입 방식
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String test() {
        return "hello";
    }

    @PostMapping
    @ApiOperation(value = "글쓰기", notes = "댓글을 작성합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글이 등록되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity postBoard(@Validated @RequestBody BoardPostReq boardPostReq,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "입력 내용을 다시 확인해주세요"));
        }

        try {
            boardService.postBoard(boardPostReq);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(BaseRes.of(400, "입력 내용을 다시 확인해주세요"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }

        return ResponseEntity.status(200).body(BaseRes.of(200, "글이 등록되었습니다"));
    }

    @PutMapping
    @ApiOperation(value = "글쓰기", notes = "댓글을 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글이 수정되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity updateBoard(@Validated @RequestBody BoardUpdateReq boardUpdateReq) throws Exception {
        try {
            boardService.updateBoard(boardUpdateReq);
        } catch (PasswordMisMatchException e1) {
            return ResponseEntity.status(400).body(BaseRes.of(400, e1.getMessage()));
        } catch (NoSuchElementException e2) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "입력 내용을 다시 확인해주세요"));
        } catch (NoSuchAlgorithmException e3) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        } catch (Exception e4) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }
        return ResponseEntity.status(200).body(BaseRes.of(200, "글이 수정되었습니다"));
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "글쓰기", notes = "댓글을 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글이 삭제되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity deleteBoard(@PathVariable long boardId, @RequestHeader(value = "Authorization") String password) {
        try {
            boardService.deleteBoard(boardId, password);
        } catch (PasswordMisMatchException e1) {
            return ResponseEntity.status(400).body(BaseRes.of(400, e1.getMessage()));
        } catch (NoSuchElementException e2) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "입력 내용을 다시 확인해주세요"));
        } catch (NoSuchAlgorithmException e3) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        } catch (Exception e4) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }
        return ResponseEntity.status(200).body(BaseRes.of(200, "글이 삭제되었습니다"));
    }


    @GetMapping("/keyword/{keywordId}")
    @ApiOperation(value = "글 조회", notes = "초기 댓글 100개를 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "초기 댓글 100개가 조회되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity<? extends BaseRes> getBoards(@PathVariable long keywordId) {
        List<BoardData> boardDataList;
        try {
            boardDataList = boardService.getBoards(keywordId);
        } catch (NoSuchElementException e1) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "NoSuchElementException 에러"));
        } catch (Exception e4) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }
        for (BoardData boardData : boardDataList) {
            System.out.println(boardData);
        }
        return ResponseEntity.status(200).body(BoardGetRes.of(200, "댓글 초기 100개 조회", boardDataList));
    }


    @GetMapping("/keyword/{keywordId}/last-board-id/{boardId}")
    @ApiOperation(value = "글 조회", notes = "추가 댓글 100개를 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추가 댓글 100개가 조회되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity<? extends BaseRes> getBoards(@PathVariable long keywordId, long boardId) {
        List<BoardData> boardDataList;
        try {
            boardDataList = boardService.getBoards(keywordId, boardId);
        } catch (NoSuchElementException e1) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "NoSuchElementException 에러"));
        } catch (Exception e4) {
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }
        return ResponseEntity.status(200).body(BoardGetRes.of(200, "댓글 추가 100개 조회", boardDataList));
    }

}
