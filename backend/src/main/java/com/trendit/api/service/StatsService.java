package com.trendit.api.service;

import com.trendit.api.response.data.BarChartData;
import com.trendit.common.exception.IllegalChartDataException;
import com.trendit.common.type.PeriodEnum;
import com.trendit.db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StatsService {
    CustomRepository customRepository;

    public List<BarChartData> getBarChartData(PeriodEnum type, int val) {
        LocalDate targetTime = type.getTargetDate(val);
        return customRepository.getFrequencyStats(type, targetTime, false);
    }

    public List<BarChartData> getCompanyBarChartData(PeriodEnum type) {
        LocalDate targetTime = type.getRecentTime();
        return customRepository.getFrequencyStats(type, targetTime, true);
    }

    public List<Integer> getLineChartData(PeriodEnum type, String keyword) throws IllegalChartDataException {
        return customRepository.getFrequencyStatsPerKeyword(type, keyword);
    }
}
