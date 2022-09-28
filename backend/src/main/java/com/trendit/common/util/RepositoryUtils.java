package com.trendit.common.util;

import com.trendit.api.response.data.BarChartData;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.api.response.data.NewsData;
import com.trendit.common.exception.IllegalChartDataException;
import com.trendit.common.type.PeriodEnum;
import com.trendit.db.entity.News;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        queryBuffer.append("and n.newsId in (select max(n.newsId) from KeywordHasNews kn join kn.news n group by kn.keyword.keywordId) ");
        queryBuffer.append("order by s.frequency desc");

        /* TODO : dependant subquery가 발생하는 문제점 -> 더 나은 해결책? */

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

    public String buildFrequencyStatsQuery(PeriodEnum type, boolean isCompany) {
        String targetEntity = type.getTargetEntity();

        StringBuffer queryBuffer = new StringBuffer("select s.keyword.keyword, s.frequency from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        if (isCompany) {
            queryBuffer.append("join s.keyword k join k.company c on k.company.companyId = c.companyId ");
        }
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

        StringBuffer queryBuffer = new StringBuffer("select s.targetTime, s.frequency from ");
        queryBuffer.append(targetEntity); // "StatisticsDate"
        queryBuffer.append(" s ");
        queryBuffer.append("where s.keyword.keywordId = :keywordId and s.targetTime >= :startDate and s.targetTime <= :lastDate ");
        queryBuffer.append("order by s.targetTime");

        String query = queryBuffer.toString();
        return query;
    }

    public List<Integer> parseFrequencyStatsPerKeywordList(List<Object[]> list, PeriodEnum type)
            throws IllegalChartDataException{

        List<Integer> data = new ArrayList<>();
        int maxNum = type.getDateConstant();
        LocalDate endDate = type.getRecentTime();
        LocalDate currentDate = type.getBeforeTime(endDate, maxNum - 1);

        /*
           최근 7일/5주/12개월/10년간의 데이터 추이를 보여준다. 이 떄 해당 키워드가 있었던 기간에는
           그 키워드의 frequency를 보여주고 아닐 경우 0으로 그 자리를 채운다.
           최종적으로 [0,10,15,40,0,20,0] (type=day일 경우) 와 같은 형태로 출력된다.
        */
        int currentPos = 0;
        while (!currentDate.isAfter(endDate)) {
            if (currentPos >= list.size()) {
                while (data.size() < maxNum) {
                    data.add(0);
                }
                break;
            }
            Object[] row = list.get(currentPos);
            LocalDate rowDate = (LocalDate) row[0];
            Integer frequency = (Integer) row[1];
            if (currentDate.isEqual(rowDate)) {
                data.add(frequency);
                currentPos++;
            }
            else {
                data.add(0);
            }
            currentDate = type.getNextTime(currentDate, 1);
        }

        if (data.size() > maxNum) {
            throw new IllegalChartDataException();
        }
        return data;
    }

}
