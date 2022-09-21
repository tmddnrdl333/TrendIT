package com.trendit.common.util;

import com.trendit.api.response.data.BarChartData;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsData;
import com.trendit.common.type.PeriodEnum;
import com.trendit.db.entity.News;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryUtils {
    public String buildKeywordNewsQuery(PeriodEnum type) {
        String targetEntity = type.getTargetEntity();

        StringBuffer queryBuffer = new StringBuffer("select s.frequency, k.keyword, n from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        queryBuffer.append("join s.keyword k ");
        queryBuffer.append("join k.keywordHasNews kn ");
        queryBuffer.append("join kn.news n ");
        queryBuffer.append("where s.targetTime = :recentTime ");
        queryBuffer.append("order by s.frequency desc");

//        SELECT s.frequency, k.keyword, n.news_id, n.headline FROM statistics_date as s JOIN keyword as k ON s.keyword_id = k.keyword_id
//        join (SELECT kn.keyword_id, max(kn.news_id) as recent_news_id from keyword_has_news kn group by keyword_id) recent_news on s.keyword_id = recent_news.keyword_id
//        JOIN news as n on n.news_id = recent_news.recent_news_id
//        where s.target_time = '2022-09-20' ORDER BY s.frequency DESC;

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

    public String buildFrequencyStatsQuery(PeriodEnum type) {
        String targetEntity = type.getTargetEntity();

        StringBuffer queryBuffer = new StringBuffer("select s.keyword.keyword, s.frequency from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        queryBuffer.append("where s.targetTime = :date ");
        queryBuffer.append("order by s.frequency desc");

        String query = queryBuffer.toString();
        return query;
    }

    public List<BarChartData> parseFrequencyStatsResultList(List<Object[]> list) {
        List<BarChartData> data = new ArrayList<>();

        for (Object[] row : list) {
            String keyword = (String)row[0];
            int frequency = (int)row[1];
            BarChartData barChartData = new BarChartData(keyword, frequency);
            data.add(barChartData);
        }

        return data;
    }

    public String buildFrequencyStatsPerKeywordQuery(PeriodEnum type) {
        String targetEntity = type.getTargetEntity();

        StringBuffer queryBuffer = new StringBuffer("select s.frequency from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        queryBuffer.append("where s.keyword.keyword = :keyword ");
        queryBuffer.append("order by s.targetTime desc");

        String query = queryBuffer.toString();
        return query;
    }

}
