package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class StatisticsYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long statisticsYearId;

    int year;

    int frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
