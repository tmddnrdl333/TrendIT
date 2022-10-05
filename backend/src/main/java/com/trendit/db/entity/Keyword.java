package com.trendit.db.entity;

import com.trendit.api.response.data.BoardData;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = @Index(name = "keyword_index", columnList = "keyword"))
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long keywordId;

    @Column(length = 20)
    String keyword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

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
