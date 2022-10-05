package com.trendit.db.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.trendit.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QNews qNews = QNews.news;
    QKeyword qKeyword = QKeyword.keyword1;
    QKeywordHasNews qKeywordHasNews = QKeywordHasNews.keywordHasNews;


    public long getTodayNewsCount() {
        LocalDate today = LocalDate.now().minusDays(1);
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

    public Page<News> getLatestNews(String newsDate, List<String> newsAgencies, Pageable pageable) {
        QueryResults<News> latestNews = jpaQueryFactory
                .select(qNews)
                .from(qNews)
                .where(inNewsAgencies(newsAgencies))
                .where(eqNewsDate(newsDate))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qNews.newsId.desc())
                .fetchResults();
        return new PageImpl<>(latestNews.getResults(), pageable, latestNews.getTotal());
    }


    private BooleanExpression eqNewsAgency(String newsAgency) {
        if (StringUtils.isEmpty(newsAgency)) {
            return null;
        }
        return qNews.newsAgency.eq(newsAgency);
    }

    private BooleanExpression inNewsAgencies(List<String> newsAgencies) {
        if (newsAgencies==null || newsAgencies.size()==0) {
            return null;
        }
        return qNews.newsAgency.in(newsAgencies);
    }

    private BooleanExpression eqNewsDate(String newsDate) {
        if (StringUtils.isEmpty(newsDate)) {
            return null;
        }
        List<LocalDate> parsedDate = parsingDate(newsDate);
        return qNews.newsDate.between(parsedDate.get(0), parsedDate.get(1));
    }

    public Page<News> getNewsByOptions(long keywordId, String newsDate, List<String> newsAgencies, Pageable pageable) {


        JPQLQuery<News> query = jpaQueryFactory
                .select(qNews)
                .from(qKeyword, qKeywordHasNews, qNews)
                .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                        qKeywordHasNews.news.newsId.eq(qNews.newsId),
                        qKeyword.keywordId.eq(keywordId), inNewsAgencies(newsAgencies), eqNewsDate(newsDate))
                .orderBy(qNews.newsId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<News> news = query.fetch();

        long totalCount = query.fetchCount();
        Page<News> ret = new PageImpl<>(news, pageable, totalCount);
        return ret;
    }

    public List<LocalDate> parsingDate(String newsDate) {
        List<LocalDate> parsedDate = new ArrayList<>();
        String startDate = newsDate.substring(0,10);
        String endDate = newsDate.substring(11);
        LocalDate localStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate localEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        parsedDate.add(localStartDate);
        parsedDate.add(localEndDate);
        return parsedDate;
    }

}
