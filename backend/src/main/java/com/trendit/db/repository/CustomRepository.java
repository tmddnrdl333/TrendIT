package com.trendit.db.repository;

import com.trendit.api.response.data.BarChartData;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.common.exception.IllegalChartDataException;
import com.trendit.common.type.PeriodEnum;
import com.trendit.common.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final RepositoryUtils repositoryUtils;

    public List<KeywordNewsData> getKeywordNews(PeriodEnum type) {
        LocalDate recentTime = type.getRecentTime(); // type == day일 경우 어제, week일경우 이번주의 첫날, month일경우 이번달의 첫날, year일 경우 이번 해의 첫날
        String query = repositoryUtils.buildKeywordNewsQuery(type);

        List<Object[]> list = (List<Object[]>) entityManager
                .createNativeQuery(query)
                .setParameter("recentTime", recentTime)
                .setMaxResults(30)
                .getResultList();

        return repositoryUtils.parseKeywordNewsResultList(list);
    }


    public List<BarChartData> getFrequencyStats(PeriodEnum type, LocalDate date, boolean isCompany) {
        String query = repositoryUtils.buildFrequencyStatsQuery(type, isCompany);

        List<Object[]> list = (List<Object[]>) entityManager
                .createQuery(query)
                .setParameter("date", date)
                .setMaxResults(10)
                .getResultList();

        return repositoryUtils.parseFrequencyStatsResultList(list);
    }


    public List<Integer> getFrequencyStatsPerKeyword(PeriodEnum type, long keywordId) throws IllegalChartDataException {
        String query = repositoryUtils.buildFrequencyStatsPerKeywordQuery(type);
        int dateNum = type.getDateConstant();

        // 주, 월, 년은 모두 이번 기간 기준인데 일만 어제 기준인 것을 반영
        if (type != PeriodEnum.day) {
            dateNum = dateNum - 1;
        }
        LocalDate startDate = type.getTargetDate(dateNum);
        LocalDate lastDate = type.getRecentTime();

        List<Object[]> tupleList = entityManager
                .createQuery(query)
                .setParameter("keywordId", keywordId)
                .setParameter("startDate", startDate)
                .setParameter("lastDate", lastDate)
                .getResultList();

        List<Integer> list = repositoryUtils.parseFrequencyStatsPerKeywordList(tupleList, type);

        return list;
    }

}
