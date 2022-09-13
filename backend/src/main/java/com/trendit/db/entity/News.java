package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long newsId;

    @Column(length = 200)
    String headline;

    @Column(length = 300)
    String newsContent;

    LocalDate newsDate;

    @Column(length = 20)
    String newsAgency;

    @Column(length = 2000)
    String newsLink;

    @Column(length = 2000)
    String imgLink;
}
