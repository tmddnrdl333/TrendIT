package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class StatisticsMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long statisticsMonthId;

    int year;

    int month;

    int frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
