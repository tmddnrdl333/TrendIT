package com.trendit.api.controller;

import com.trendit.api.response.KeywordNewsGetRes;
import com.trendit.api.response.NewsCountGetRes;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsCountData;
import com.trendit.api.service.KeywordService;
import com.trendit.api.service.NewsService;
import com.trendit.db.entity.News;
import com.trendit.db.repository.NewsRepositorySupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatsController {

    NewsService newsService;

    KeywordService keywordService;

    @GetMapping("/news-count")
    @ApiOperation(value = "금일/전체 뉴스 개수 조회", notes = "오늘 수집한 뉴스 기사 수, 누적 기사 수를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "개수 조회에 성공했습니다.")
    })
    public ResponseEntity getNewsCount() {
        NewsCountData data = newsService.getNewsCountData();
        return ResponseEntity.status(200).body(NewsCountGetRes.of(200, "Success", data));
    }

    @GetMapping("/wordcloud/{type}")
    public ResponseEntity getKeywordNews(@PathVariable String type) {
        List<KeywordNewsData> data = keywordService.getKeywordNews(type);
        return ResponseEntity.status(200).body(KeywordNewsGetRes.of(200, "Success", data));
    }
}