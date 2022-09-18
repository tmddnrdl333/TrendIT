package com.trendit.api.service;

import com.trendit.api.response.data.BarChartData;
import com.trendit.common.exception.IllegalChartDataTypeException;
import com.trendit.db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StatsService {
    CustomRepository customRepository;

    public List<BarChartData> getBarChartData(String type, int val) throws Exception{
        LocalDate targetTime = LocalDate.now();
        List<BarChartData> barChartDataList;
        if ("day".equals(type)) {
            int dayOffset = 7 - val;
            LocalDate targetDay = targetTime.minusDays(dayOffset);
            /* TODO */
            barChartDataList = customRepository.getFrequencyStats(type, targetDay);
        } else if ("week".equals(type)) {
            int weekOffset = 5 - val;
            LocalDate targetWeek = targetTime.minusWeeks(weekOffset);
            targetWeek = targetWeek.minusDays(targetWeek.getDayOfWeek().getValue() - 1);
            barChartDataList = customRepository.getFrequencyStats(type, targetWeek);
        } else if ("month".equals(type)) {
            int monthOffset = 12 - val;
            LocalDate targetMonth = targetTime.minusMonths(monthOffset);
            targetMonth = targetMonth.minusDays(targetMonth.getDayOfMonth() - 1);
            barChartDataList = customRepository.getFrequencyStats(type, targetMonth);
        } else if ("year".equals(type)) {
            int yearOffSet = 10 - val;
            LocalDate targetYear = targetTime.minusYears(yearOffSet);
            targetYear = targetYear.minusMonths(targetYear.getMonthValue() - 1);
            targetYear = targetYear.minusDays(targetYear.getDayOfMonth() - 1);
            barChartDataList = customRepository.getFrequencyStats(type, targetYear);
        } else {
            // error
            throw new IllegalChartDataTypeException();
        }
        return barChartDataList;
    }

    public List<Integer> getLineChartData(String type, String keyword) throws Exception {
        if ("day".equals(type)) {
            return customRepository.getFrequencyStatsPerKeyword(type, keyword, 7);
        } else if ("week".equals(type)) {
            return customRepository.getFrequencyStatsPerKeyword(type, keyword, 5);
        } else if ("month".equals(type)) {
            return customRepository.getFrequencyStatsPerKeyword(type, keyword, 12);
        } else if ("year".equals(type)) {
            return customRepository.getFrequencyStatsPerKeyword(type, keyword, 10);
        } else {
            throw new IllegalChartDataTypeException();
        }
    }
}
