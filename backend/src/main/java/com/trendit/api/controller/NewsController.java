package com.trendit.api.controller;

import com.trendit.api.response.LatestNewsGetRes;
import com.trendit.api.service.NewsService;
import com.trendit.db.entity.News;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
