package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class StatisticsDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long statisticsDateId;

    int year;

    int month;

    int day;

    int frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
