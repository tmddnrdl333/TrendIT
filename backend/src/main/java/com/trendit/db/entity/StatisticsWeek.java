package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class StatisticsWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long statisticsWeekId;

    LocalDate week; // 주의 첫 날

    int frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
