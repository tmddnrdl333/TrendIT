package com.trendit.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.BarChartData;
import com.trendit.db.entity.QStatisticsDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsDateRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    QStatisticsDate qStatisticsDate = QStatisticsDate.statisticsDate;

    public List<BarChartData> getDailyStats(LocalDate date) {
        List<BarChartData> barChartDataList = new ArrayList<>();
        List<Tuple> tuples = jpaQueryFactory
                .select(qStatisticsDate.keyword.keyword, qStatisticsDate.frequency)
                .from(qStatisticsDate)
                .where(qStatisticsDate.targetTime.eq(date))
                .orderBy(qStatisticsDate.frequency.desc())
                .limit(10)
                .fetch();

        for (Tuple tuple : tuples) {
            String keyword = tuple.get(qStatisticsDate.keyword.keyword);
            long frequency = tuple.get(qStatisticsDate.keyword.count());
            barChartDataList.add(new BarChartData(keyword, frequency));
        }

        return barChartDataList;
    }

    public List<Integer> getDailyStatsPerKeyword(String keyword) {
        List<Integer> lineChartDataList = jpaQueryFactory
                .select(qStatisticsDate.frequency)
                .from(qStatisticsDate)
                .where(qStatisticsDate.keyword.keyword.eq(keyword))
                .orderBy(qStatisticsDate.targetTime.desc())
                .limit(7)
                .fetch();

        Collections.reverse(lineChartDataList);
        return lineChartDataList;
    }


}
