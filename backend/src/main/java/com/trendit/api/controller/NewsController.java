package com.trendit.api.controller;

import com.trendit.api.response.BaseRes;
import com.trendit.api.response.BoardGetRes;
import com.trendit.api.response.LatestNewsGetRes;
import com.trendit.api.response.NewsGetRes;
import com.trendit.db.entity.News;
import com.trendit.db.repository.NewsRepositorySupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.trendit.api.service.NewsService;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    NewsService newsService;

    @GetMapping("/latest")
    public ResponseEntity getLatestNews() {
        List<News> data = newsService.getLatestNews();
        return ResponseEntity.status(200).body(LatestNewsGetRes.of(200, "Success", data));
    }

    @GetMapping("")
    @ApiOperation(value = "기사 조회", notes = "기사 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기사가 조회되었습니다"),
            @ApiResponse(code = 400, message = "입력 내용을 다시 확인해주세요"),
            @ApiResponse(code = 500, message = "오류가 발생했습니다")
    })
    public ResponseEntity<? extends BaseRes> getNews(@RequestParam("keyword") String keyword,
                                           @RequestParam(value = "newsDate", required = false) String newsDate,
                                           @RequestParam(value = "newsAgency", required = false) String newsAgency,
                                           @RequestParam("page") int page) {
        List<News> news;
        try {
            news = newsRepositorySupport.getNews(keyword, newsDate, newsAgency, page);
        } catch (NoSuchElementException e1) {
            e1.printStackTrace();
            return ResponseEntity.status(400).body(BaseRes.of(400, "입력 내용을 다시 확인해주세요"));
        } catch (Exception e4) {
            e4.printStackTrace();
            return ResponseEntity.status(500).body(BaseRes.of(500, "오류가 발생했습니다"));
        }
        return ResponseEntity.status(200).body(NewsGetRes.of(200, "기사가 조회되었습니다", news));

    }
}
