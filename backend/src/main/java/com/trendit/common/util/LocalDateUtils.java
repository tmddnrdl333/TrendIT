package com.trendit.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateUtils {
    public static LocalDate getFirstDayOfTargetWeek(LocalDate targetTime) {
        // 정책 상 월요일에는 저번 주 통계를 표시한다.
        if (targetTime.getDayOfWeek() == DayOfWeek.MONDAY) {
            targetTime = targetTime.minusDays(1);
        }
        return targetTime.minusDays(targetTime.getDayOfWeek().getValue() - 1);
    }
    public static LocalDate getFirstDayOfTargetMonth(LocalDate targetTime) {
        // 정책 상 1일일 경우 저번달의 통계를 표시한다.
        if (targetTime.getDayOfMonth() == 1) {
            targetTime = targetTime.minusDays(1);
        }
        return targetTime.minusDays(targetTime.getDayOfMonth() - 1);
    }

    public static LocalDate getFirstDayOfTargetYear(LocalDate targetTime) {
        // 정책 상 1월 1일일 경우 작년 통계를 표시한다.
        if (targetTime.getDayOfYear() == 1) {
            targetTime = targetTime.minusDays(1);
        }
        targetTime = targetTime.minusMonths(targetTime.getMonthValue() - 1);
        return getFirstDayOfTargetMonth(targetTime);
    }
}
