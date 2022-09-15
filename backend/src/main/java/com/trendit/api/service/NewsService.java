package com.trendit.api.service;

import com.trendit.api.response.data.NewsCountData;
import com.trendit.api.response.data.NewsData;
import com.trendit.db.entity.News;
import com.trendit.db.repository.NewsRepositorySupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    NewsRepositorySupport newsRepositorySupport;

    public NewsCountData getNewsCountData() {
        long todayNewsCount = newsRepositorySupport.getTodayNewsCount();
        long totalNewsCount = newsRepositorySupport.getTotalNewsCount();
        NewsCountData data = new NewsCountData(todayNewsCount,totalNewsCount);
        return data;
    }

    public List<News> getLatestNews() {
        List<News> data = newsRepositorySupport.getLatestNews();
        return data;
    }
}