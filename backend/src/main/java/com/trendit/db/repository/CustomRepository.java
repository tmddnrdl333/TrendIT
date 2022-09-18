package com.trendit.db.repository;

import com.trendit.api.response.data.BarChartData;
import com.trendit.api.response.data.KeywordNewsData;
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
                .createQuery(query)
                .setParameter("recentTime", recentTime)
                .setMaxResults(30)
                .getResultList();

        return repositoryUtils.parseKeywordNewsResultList(list);
    }


    public List<BarChartData> getFrequencyStats(PeriodEnum type, LocalDate date) {
        String query = repositoryUtils.buildFrequencyStatsQuery(type);

        List<Object[]> list = (List<Object[]>) entityManager
                .createQuery(query)
                .setParameter("date", date)
                .setMaxResults(10)
                .getResultList();

        return repositoryUtils.parseFrequencyStatsResultList(list);
    }


    public List<Integer> getFrequencyStatsPerKeyword(PeriodEnum type, String keyword) {
        String query = repositoryUtils.buildFrequencyStatsPerKeywordQuery(type);

        int limitNum = type.getDateConstant();

        List<Integer> list = entityManager
                .createQuery(query)
                .setParameter("keyword", keyword)
                .setMaxResults(limitNum)
                .getResultList();

        Collections.reverse(list);
        return list;
    }

}
