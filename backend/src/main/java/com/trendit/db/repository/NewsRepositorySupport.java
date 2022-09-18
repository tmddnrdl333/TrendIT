package com.trendit.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.trendit.db.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

    /* TODO: LocalDate는 시간 순서대로 정렬이 되지 않음 */
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


    private BooleanExpression eqNewsAgency(String newsAgency) {
        if (StringUtils.isEmpty(newsAgency)) {
            return null;
        }
        return qNews.newsAgency.eq(newsAgency);
    }

    private BooleanExpression eqNewsDate(String newsDate) {
        if (StringUtils.isEmpty(newsDate)) {
            return null;
        }
        List<LocalDate> parsedDate = parsingDate(newsDate);
        return qNews.newsDate.between(parsedDate.get(0), parsedDate.get(1));
    }

    public List<News> getNewsByOptions(String keyword, String newsDate, String newsAgency, int page) {
        List<News> news = new ArrayList<>();

//        List<News> news = jpaQueryFactory
//                .select(qNews)
//                .from(qKeyword, qKeywordHasNews, qNews)
//                .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
//                        qKeywordHasNews.news.newsId.eq(qNews.newsId),
//                        qKeyword.keyword.eq(keyword),
//                        eqNewsAgency(newsAgency),
//                        eqNewsDate(newsDate))
//                .orderBy(qNews.newsId.desc())
//                .offset(10 * (page - 1))
//                .limit(10)
//                .fetch();

        if (newsDate != null && newsAgency != null) {
            List<LocalDate> parsedDate = parsingDate(newsDate);


            news = jpaQueryFactory
                    .select(qNews)
                    .from(qKeyword, qKeywordHasNews, qNews)
                    .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                            qKeywordHasNews.news.newsId.eq(qNews.newsId),
                            qKeyword.keyword.eq(keyword),
                            qNews.newsDate.between(parsedDate.get(0), parsedDate.get(1)),
                            qNews.newsAgency.eq(newsAgency))
                    .orderBy(qNews.newsId.desc())
                    .offset(10 * (page - 1))
                    .limit(10)
                    .fetch();
            return news;
        }

        if (newsDate != null && newsAgency == null) {
            List<LocalDate> parsedDate = parsingDate(newsDate);
            news = jpaQueryFactory
                    .select(qNews)
                    .from(qKeyword, qKeywordHasNews, qNews)
                    .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                            qKeywordHasNews.news.newsId.eq(qNews.newsId),
                            qKeyword.keyword.eq(keyword),
                            qNews.newsDate.between(parsedDate.get(0), parsedDate.get(1)))
                    .orderBy(qNews.newsId.desc())
                    .offset(10 * (page - 1))
                    .limit(10)
                    .fetch();
            return news;
        }

        if (newsDate == null && newsAgency != null) {
            news = jpaQueryFactory
                    .select(qNews)
                    .from(qKeyword, qKeywordHasNews, qNews)
                    .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                            qKeywordHasNews.news.newsId.eq(qNews.newsId),
                            qKeyword.keyword.eq(keyword),
                            qNews.newsAgency.eq(newsAgency))
                    .orderBy(qNews.newsId.desc())
                    .offset(10 * (page - 1))
                    .limit(10)
                    .fetch();
            return news;
        }

        if (newsDate == null && newsAgency == null) {
            news = jpaQueryFactory
                    .select(qNews)
                    .from(qKeyword, qKeywordHasNews, qNews)
                    .where(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId),
                            qKeywordHasNews.news.newsId.eq(qNews.newsId),
                            qKeyword.keyword.eq(keyword))
                    .orderBy(qNews.newsId.desc())
                    .offset(10 * (page - 1))
                    .limit(10)
                    .fetch();
            return news;
        }
        return news;
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
