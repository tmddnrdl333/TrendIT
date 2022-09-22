package com.trendit.api.service;

import com.trendit.api.response.data.NewsCountData;
import com.trendit.db.entity.News;
import com.trendit.db.repository.NewsRepositorySupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<News> getLatestNews(String newsAgency, Pageable pageable) {
        Page<News> data = newsRepositorySupport.getLatestNews(newsAgency, pageable);
        return data;
    }

    public List<News> getNews(String keyword, String newsDate, String newsAgency, int page) {
        List<News> news = newsRepositorySupport.getNewsByOptions(keyword, newsDate, newsAgency, page);
        return news;
    }
}