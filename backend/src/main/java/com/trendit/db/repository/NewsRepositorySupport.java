package com.trendit.db.repository;

import aj.org.objectweb.asm.Label;
import com.trendit.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QNews qNews = QNews.news;
    QKeyword qKeyword = QKeyword.keyword1;
    QKeywordHasNews qKeywordHasNews = QKeywordHasNews.keywordHasNews;


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

    public List<News> getNews(String keyword, String newsDate, String newsAgency, int page) {
        List<News> news = new ArrayList<>();
        String startDate = newsDate.substring(0,10);
        String endDate = newsDate.substring(11);
        LocalDate localStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate localEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        if (newsDate != null && newsAgency != null) {
            news = jpaQueryFactory
                    .select(qNews)
                    .from(qKeyword, qKeywordHasNews, qNews)
                    .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                            qKeywordHasNews.news.newsId.eq(qNews.newsId),
                            qKeyword.keyword.eq(keyword),
                            qNews.newsDate.between(localStartDate, localEndDate),
                            qNews.newsAgency.eq(newsAgency))
                    .orderBy(qNews.newsId.desc())
                    .offset(10 * (page - 1))
                    .limit(10)
                    .fetch();
            return news;
        }
        return news;

    }

}
