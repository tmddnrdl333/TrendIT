package com.trendit.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.BarChartData;
import com.trendit.db.entity.QStatisticsWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsWeekRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    QStatisticsWeek qStatisticsWeek = QStatisticsWeek.statisticsWeek;

    public List<BarChartData> getWeeklyStats(LocalDate date) {
        List<BarChartData> barChartDataList = new ArrayList<>();
        List<Tuple> tuples = jpaQueryFactory
                .select(qStatisticsWeek.keyword.keyword, qStatisticsWeek.frequency)
                .from(qStatisticsWeek)
                .where(qStatisticsWeek.targetTime.eq(date))
                .orderBy(qStatisticsWeek.frequency.desc())
                .limit(10)
                .fetch();

        for (Tuple tuple : tuples) {
            String keyword = tuple.get(qStatisticsWeek.keyword.keyword);
            long frequency = tuple.get(qStatisticsWeek.keyword.count());
            barChartDataList.add(new BarChartData(keyword, frequency));
        }

        return barChartDataList;
    }
}
