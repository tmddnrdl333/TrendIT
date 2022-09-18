package com.trendit.api.service;

import com.trendit.api.response.data.BarChartData;
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
        return customRepository.getFrequencyStats(type, targetTime);
    }

    public List<Integer> getLineChartData(PeriodEnum type, String keyword) {
        return customRepository.getFrequencyStatsPerKeyword(type, keyword);
    }
}
