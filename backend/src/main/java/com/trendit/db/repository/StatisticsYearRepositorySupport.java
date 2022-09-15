package com.trendit.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.BarChartData;
import com.trendit.db.entity.QStatisticsYear;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsYearRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    QStatisticsYear qStatisticsYear = QStatisticsYear.statisticsYear;

    public List<BarChartData> getYearlyStats(LocalDate date) {
        List<BarChartData> barChartDataList = new ArrayList<>();
        List<Tuple> tuples = jpaQueryFactory
                .select(qStatisticsYear.keyword.keyword, qStatisticsYear.frequency)
                .from(qStatisticsYear)
                .where(qStatisticsYear.targetTime.eq(date))
                .orderBy(qStatisticsYear.frequency.desc())
                .limit(10)
                .fetch();

        for (Tuple tuple : tuples) {
            String keyword = tuple.get(qStatisticsYear.keyword.keyword);
            long frequency = tuple.get(qStatisticsYear.frequency);
            barChartDataList.add(new BarChartData(keyword, frequency));
        }

        return barChartDataList;
    }

    public List<Integer> getYearlyStatsPerKeyword(String keyword) {
        List<Integer> lineChartDataList = jpaQueryFactory
                .select(qStatisticsYear.frequency)
                .from(qStatisticsYear)
                .where(qStatisticsYear.keyword.keyword.eq(keyword))
                .orderBy(qStatisticsYear.targetTime.desc())
                .limit(10)
                .fetch();

        Collections.reverse(lineChartDataList);

        return lineChartDataList;
    }
}
