package com.trendit.common.util;

import java.time.LocalDate;

public class LocalDateUtils {
    public static LocalDate getFirstDayOfTargetWeek(LocalDate targetTime) {
        return targetTime.minusDays(targetTime.getDayOfWeek().getValue() - 1);
    }
    public static LocalDate getFirstDayOfTargetMonth(LocalDate targetTime) {
        return targetTime.minusDays(targetTime.getDayOfMonth() - 1);
    }

    public static LocalDate getFirstDayOfTargetYear(LocalDate targetTime) {
        targetTime = targetTime.minusMonths(targetTime.getMonthValue() - 1);
        return getFirstDayOfTargetMonth(targetTime);
    }
}
