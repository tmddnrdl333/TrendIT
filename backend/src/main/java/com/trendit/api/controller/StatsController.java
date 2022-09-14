package com.trendit.api.controller;

import com.trendit.api.response.NewsCountGetRes;
import com.trendit.db.repository.NewsRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    NewsRepositorySupport newsRepositorySupport;

    public ResponseEntity getNewsCount() {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());

        // News 에서 count 가져오기
        long todayNewsCount = newsRepositorySupport.getTodayNewsCount();
        long totalNewsCount = newsRepositorySupport.getTotalNewsCount();

        return ResponseEntity.status(200).body(NewsCountGetRes.of(200, "Success", todayNewsCount, totalNewsCount));
    }
}