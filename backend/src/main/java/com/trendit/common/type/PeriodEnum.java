package com.trendit.common.type;

import com.trendit.common.util.LocalDateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public enum PeriodEnum {
    day(7, "StatisticsDate"),
    week(5, "StatisticsWeek"),
    month(12, "StatisticsMonth"),
    year(10, "StatisticsYear");

    private final int dateConstant;
    private final String targetEntity;

//    private int offset(int val) {
//        return dateConstant - val;
//    }

    public LocalDate getTargetDate(int timeOffset) {
        LocalDate targetTime = LocalDate.now();
        if (this == day) {
            return targetTime.minusDays(timeOffset);
        } else if (this == week) {
            targetTime = targetTime.minusWeeks(timeOffset);
            return LocalDateUtils.getFirstDayOfTargetWeek(targetTime);
        } else if (this == month) {
            targetTime = targetTime.minusMonths(timeOffset);
            return LocalDateUtils.getFirstDayOfTargetMonth(targetTime);
        } else { // this == year
            targetTime = targetTime.minusYears(timeOffset);
            return LocalDateUtils.getFirstDayOfTargetYear(targetTime);
        }
    }

    public LocalDate getRecentTime() {
        LocalDate recentTime = LocalDate.now();
        if (this == day) {
            return recentTime.minusDays(1);
        } else if (this == week) {
            return LocalDateUtils.getFirstDayOfTargetWeek(recentTime);
        } else if (this == month) {
            return LocalDateUtils.getFirstDayOfTargetMonth(recentTime);
        } else { // this == year
            return LocalDateUtils.getFirstDayOfTargetYear(recentTime);
        }
    }

    public LocalDate getNextTime(LocalDate currentTime, int offset) {
        if (this == day) {
            return currentTime.plusDays(offset);
        } else if (this == week) {
            return currentTime.plusWeeks(offset);
        } else if (this == month) {
            return currentTime.plusMonths(offset);
        } else { // this == year
            return currentTime.plusYears(offset);
        }
    }

    public LocalDate getBeforeTime(LocalDate currentTime, int offset) {
        if (this == day) {
            return currentTime.minusDays(offset);
        } else if (this == week) {
            return currentTime.minusWeeks(offset);
        } else if (this == month) {
            return currentTime.minusMonths(offset);
        } else { // this == year
            return currentTime.minusYears(offset);
        }
    }

}
