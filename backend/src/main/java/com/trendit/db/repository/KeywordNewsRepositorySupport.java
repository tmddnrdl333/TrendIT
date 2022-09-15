package com.trendit.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsData;
import com.trendit.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KeywordNewsRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QStatisticsDate qStatisticsDate = QStatisticsDate.statisticsDate;

    QNews qNews = QNews.news;

    QKeyword qKeyword = QKeyword.keyword1;

    QKeywordHasNews qKeywordHasNews = QKeywordHasNews.keywordHasNews;

    /*
      TODO
      day, week, month, year 분기 해주삼
     */

    public List<KeywordNewsData> getKeywordNews(String type) {
        if (type.equals("day")) {
        } else {
            return null;
        }
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Tuple> tuples = jpaQueryFactory
                .select(qStatisticsDate, qKeyword, qKeywordHasNews, qNews)
                .from(qStatisticsDate)
                .where(qStatisticsDate.targetTime.eq(yesterday))
                .leftJoin(qKeyword).on(qStatisticsDate.keyword.keywordId.eq(qKeyword.keywordId))
                .leftJoin(qKeywordHasNews).on(qKeyword.keywordId.eq(qKeywordHasNews.keyword.keywordId))
                .leftJoin(qNews).on(qKeywordHasNews.news.newsId.eq(qNews.newsId))
                .orderBy(qStatisticsDate.frequency.desc())
                .limit(30)
                .fetch();

        List<KeywordNewsData> data = new ArrayList<>();
        for (Tuple tuple : tuples) {
            StatisticsDate statisticsDate = tuple.get(qStatisticsDate);
            Keyword keyword = tuple.get(qKeyword);
            KeywordHasNews keywordHasNews = tuple.get(qKeywordHasNews);
            News news = tuple.get(qNews);
            NewsData newsData = new NewsData(news.getNewsId(), news.getHeadline(), news.getNewsContent(), news.getNewsDate(), news.getNewsAgency(), news.getNewsLink(), news.getImgLink());
            KeywordNewsData keywordNewsData = new KeywordNewsData(keyword.getKeyword(), statisticsDate.getFrequency(), newsData);
            data.add(keywordNewsData);
        }
        return data;
    }

    /*
    public

    KeywordNewsData가 아닌
    select k.keyword, k.count, n.headline, n.news_content, n.news_date, n.news_date, n.news_agency, n.news_link, n.img_link
    from keyword k
    join news n
    on

    ... news는 일단 하루치를 먼저 가져와놓고,
    그 하루치 news에서 keyword_has_news 테이블로 id,id 쌍으로 keyword와 news를 가져와야 함

     */
}
