package com.trendit.db.repository;

import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsData;
import com.trendit.db.entity.News;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public String buildKeywordNewsQuery(String type) {
        String targetEntity;
        if ("day".equals(type)) {
            targetEntity = "StatisticsDate";
        } else if ("week".equals(type)) {
            targetEntity = "StatisticsWeek";
        } else if ("month".equals(type)) {
            targetEntity = "StatisticsMonth";
        } else if ("year".equals(type)) {
            targetEntity = "StatisticsYear";
        } else {
            return null;
        }

        StringBuffer queryBuffer = new StringBuffer("select s.frequency, k.keyword, n from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        queryBuffer.append("join s.keyword k ");
        queryBuffer.append("join k.keywordHasNews kn ");
        queryBuffer.append("join kn.news n ");
        queryBuffer.append("where s.targetTime = :yesterday ");
        queryBuffer.append("order by s.frequency desc");

        String query = queryBuffer.toString();
        return query;
    }

    public List<KeywordNewsData> parseKeywordNewsResultList(List<Object[]> list) {
        List<KeywordNewsData> data = new ArrayList<>();

        for (Object[] row : list) {
            int frequency = (int)row[0];
            String keyword = (String)row[1];
            News news = (News)row[2];

            NewsData newsData = new NewsData(news.getNewsId(), news.getHeadline(), news.getNewsContent(), news.getNewsDate(), news.getNewsAgency(), news.getNewsLink(), news.getImgLink());
            KeywordNewsData keywordNewsData = new KeywordNewsData(keyword, frequency, newsData);
            data.add(keywordNewsData);
        }

        return data;
    }

    public List<KeywordNewsData> getKeywordNews(String type) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String query = buildKeywordNewsQuery(type);

        List<Object[]> list = (List<Object[]>) entityManager
                .createQuery(query)
                .setParameter("yesterday", yesterday)
                .setMaxResults(30)
                .getResultList();

        return parseKeywordNewsResultList(list);
    }
}
