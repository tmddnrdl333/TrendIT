package com.trendit.db.repository;

import com.trendit.api.response.data.BarChartData;
import com.trendit.api.response.data.KeywordNewsData;
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

    public List<KeywordNewsData> getKeywordNews(String type) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String query = repositoryUtils.buildKeywordNewsQuery(type);

        List<Object[]> list = (List<Object[]>) entityManager
                .createQuery(query)
                .setParameter("yesterday", yesterday)
                .setMaxResults(30)
                .getResultList();

        return repositoryUtils.parseKeywordNewsResultList(list);
    }


    public List<BarChartData> getFrequencyStats(String type, LocalDate date) {
        String query = repositoryUtils.buildFrequencyStatsQuery(type);

        List<Object[]> list = (List<Object[]>) entityManager
                .createQuery(query)
                .setParameter("date", date)
                .setMaxResults(10)
                .getResultList();

        return repositoryUtils.parseFrequencyStatsResultList(list);
    }


    public List<Integer> getFrequencyStatsPerKeyword(String type, String keyword, int limitNum) {
        String query = repositoryUtils.buildFrequencyStatsPerKeywordQuery(type);

        List<Integer> list = entityManager
                .createQuery(query)
                .setParameter("keyword", keyword)
                .setMaxResults(limitNum)
                .getResultList();

        Collections.reverse(list);
        return list;
    }

}
