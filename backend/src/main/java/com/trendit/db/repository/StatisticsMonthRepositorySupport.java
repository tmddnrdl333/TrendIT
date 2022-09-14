package com.trendit.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.BarChartData;
import com.trendit.db.entity.QStatisticsMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsMonthRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    QStatisticsMonth qStatisticsMonth = QStatisticsMonth.statisticsMonth;

    public List<BarChartData> getMonthlyStats(LocalDate date) {
        List<BarChartData> barChartDataList = new ArrayList<>();
        List<Tuple> tuples = jpaQueryFactory
                .select(qStatisticsMonth.keyword.keyword, qStatisticsMonth.frequency)
                .from(qStatisticsMonth)
                .where(qStatisticsMonth.targetTime.eq(date))
                .orderBy(qStatisticsMonth.frequency.desc())
                .limit(10)
                .fetch();

        for (Tuple tuple : tuples) {
            String keyword = tuple.get(qStatisticsMonth.keyword.keyword);
            long frequency = tuple.get(qStatisticsMonth.keyword.count());
            barChartDataList.add(new BarChartData(keyword, frequency));
        }

        return barChartDataList;
    }

    public List<Integer> getMonthlyStatsPerKeyword(String keyword) {
        List<Integer> lineChartDataList = jpaQueryFactory
                .select(qStatisticsMonth.frequency)
                .from(qStatisticsMonth)
                .where(qStatisticsMonth.keyword.keyword.eq(keyword))
                .orderBy(qStatisticsMonth.targetTime.desc())
                .limit(12)
                .fetch();

        Collections.reverse(lineChartDataList);
        return lineChartDataList;
    }
}
