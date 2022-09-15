package com.trendit.api.controller;

import com.trendit.api.response.LatestNewsGetRes;
import com.trendit.db.entity.News;
import com.trendit.db.repository.NewsRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsRepositorySupport newsRepositorySupport;

    @GetMapping("/latest")
    public ResponseEntity getLatestNews() {

        List<News> data = newsRepositorySupport.getLatestNews();

        return ResponseEntity.status(200).body(LatestNewsGetRes.of(200, "Success", data));
    }

}
