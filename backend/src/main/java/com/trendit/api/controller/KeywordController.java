package com.trendit.api.controller;

import com.trendit.api.response.KeywordListGetRes;
import com.trendit.api.response.data.KeywordData;
import com.trendit.api.service.KeywordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyword")
@AllArgsConstructor
public class KeywordController {

    KeywordService keywordService;

    @GetMapping("")
    @ApiOperation(value = "키워드 조회", notes = "입력한 문자열을 포함한 키워드들을 리스트로 조회합니다.")
    @ApiResponses({
            @ApiResponse(code=200,message = "키워드 조회에 성공했습니다."),
            @ApiResponse(code=400, message = "해당하는 키워드가 없습니다."),
            @ApiResponse(code=500, message = "서버 에러 발생.")
    })
    public ResponseEntity getKeywordList(@RequestParam("keyword")String keyword){
        List<KeywordData> data = keywordService.getKeywordList(keyword);
        return ResponseEntity.status(200).body(KeywordListGetRes.of(200,"Success",data));
    }
}
