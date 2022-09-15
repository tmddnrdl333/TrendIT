package com.trendit.api.controller;

import com.trendit.api.response.BarChartGetRes;
import com.trendit.api.response.LineChartGetRes;
import com.trendit.api.response.NewsCountGetRes;
import com.trendit.api.response.data.BarChartData;
import com.trendit.api.service.StatsService;
import com.trendit.common.exception.IllegalChartDataTypeException;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.repository.NewsRepositorySupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import com.trendit.api.response.KeywordNewsGetRes;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsCountData;
import com.trendit.api.service.KeywordService;
import com.trendit.api.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatsController {

    NewsRepositorySupport newsRepositorySupport;

    StatsService statsService;

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

    @GetMapping("/bar-chart/{type}/{val}")
    @ApiOperation(value = "bar-chart 생성에 필요한 데이터")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "서버 오류 발생.")
    })
    public ResponseEntity getBarChartData(
            @ApiParam(value = "type: day/week/month/year", required = true)
            @PathVariable String type,
            @ApiParam(value = "val: 그래프의 슬라이더의 값을 그대로 입력," +
                    "type = day이고 val = 1이면 6일 전, 7이면 오늘", required = true)
            @PathVariable int val) {
        List<BarChartData> barChartDataList;
        try {
            barChartDataList = statsService.getBarChartData(type, val);
        } catch (IllegalChartDataTypeException e) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "Bad Request"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseRes.of(500, "서버 에러 발생."));
        }

        return ResponseEntity.status(200).body(BarChartGetRes.of(200, "Success", barChartDataList));
    }

    @GetMapping("/line-chart/{type}/{keyword}")
    @ApiOperation(value = "line-chart 생성에 필요한 데이터")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "서버 에러 발생.")
    })
    public ResponseEntity getLineChartData(
            @ApiParam(value = "type: day/week/month/year", required = true)
            @PathVariable String type,
            @ApiParam(value = "keyword : 키워드 입력", required = true)
            @PathVariable String keyword) {

        List<Integer> lineChartDataList;
        try {
            lineChartDataList = statsService.getLineChartData(type, keyword);
        } catch (IllegalChartDataTypeException e) {
            return ResponseEntity.status(400).body(BaseRes.of(400, "Bad Request"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseRes.of(500, "서버 에러 발생."));
        }

        return ResponseEntity.status(200).body(LineChartGetRes.of(200, "Success", lineChartDataList));
    }
}