package com.trendit.api.controller;

import com.trendit.api.response.BarChartGetRes;
import com.trendit.api.response.LineChartGetRes;
import com.trendit.api.response.NewsCountGetRes;
import com.trendit.api.response.data.BarChartData;
import com.trendit.api.service.StatsService;
import com.trendit.db.repository.NewsRepositorySupport;
import lombok.AllArgsConstructor;
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
    NewsRepositorySupport newsRepositorySupport;

    StatsService statsService;

    public ResponseEntity getNewsCount() {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());

        // News 에서 count 가져오기
        long todayNewsCount = newsRepositorySupport.getTodayNewsCount();
        long totalNewsCount = newsRepositorySupport.getTotalNewsCount();

        return ResponseEntity.status(200).body(NewsCountGetRes.of(200, "Success", todayNewsCount, totalNewsCount));
    }

    @GetMapping("/bar-chart/{type}/{val}")
    public ResponseEntity getBarChartData(@PathVariable String type, @PathVariable int val) {
        List<BarChartData> barChartDataList = statsService.getBarChartData(type, val);

        return ResponseEntity.status(200).body(BarChartGetRes.of(200, "Success", barChartDataList));
    }

    @GetMapping("/line-chart/{type}/{keyword}")
    public ResponseEntity getLineChartData(@PathVariable String type, @PathVariable String keyword) {
        List<Integer> lineChartDataList = statsService.getLineChartData(type, keyword);

        return ResponseEntity.status(200).body(LineChartGetRes.of(200, "Success", lineChartDataList));
    }
}