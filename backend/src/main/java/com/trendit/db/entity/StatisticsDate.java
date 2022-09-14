package com.trendit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StatisticsDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long statisticsDateId;

    @Column(nullable = false)
    LocalDate targetTime;

    @Column(nullable = false)
    int frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
