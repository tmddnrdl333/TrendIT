package com.trendit.api.controller;

import com.trendit.api.request.BoardPostReq;
import com.trendit.api.response.BaseRes;
import com.trendit.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

}
