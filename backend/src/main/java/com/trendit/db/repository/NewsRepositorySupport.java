package com.trendit.db.repository;

import com.trendit.db.entity.News;
import com.trendit.db.entity.QKeyword;
import com.trendit.db.entity.QNews;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QNews qNews = QNews.news;

    public long getTodayNewsCount() {
        LocalDate today = LocalDate.now();
        long todayNewsCount = jpaQueryFactory
                .select(qNews)
                .from(qNews)
                .where(qNews.newsDate.eq(today))
                .fetchCount();
        return todayNewsCount;
    }

    public long getTotalNewsCount() {
        long totalNewsCount = jpaQueryFactory
                .select(qNews)
                .from(qNews)
                .fetchCount();
        return totalNewsCount;
    }

    public List<News> getLatestNews() {
        List<News> latestNews = new ArrayList<>();
        latestNews = jpaQueryFactory
                .select(qNews)
                .from(qNews)
                .limit(10) // 한 페이지만 보여주는 건지? 보여주는 개수 변수로 바꿀지?
                .orderBy(qNews.newsDate.desc())
                .fetch();
        return latestNews;
    }

}