package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long keywordId;

    @Column(length = 20)
    String keyword;

    boolean isCompany;

    @OneToMany(mappedBy = "keyword")
    List<KeywordHasNews> keywordHasNews = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<StatisticsYear> statisticsYears = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<StatisticsMonth> statisticsMonths = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<StatisticsWeek> statisticsWeeks = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<StatisticsDate> statisticsDates = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<Board> boards = new ArrayList<>();

}
