package com.trendit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "news_date_index", columnList = "newsDate"),
        @Index(name = "news_date_and_agency_index", columnList = "newsDate, newsAgency")
})
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long newsId;

    @Column(length = 200, nullable = false)
    String headline;

    @Column(length = 300, nullable = false)
    String newsContent;

    @Column(nullable = false)
    LocalDate newsDate;

    @Column(length = 20, nullable = false)
    String newsAgency;

    @Column(length = 2000, nullable = false)
    String newsLink;

    @Column(length = 2000)
    String imgLink;
}
