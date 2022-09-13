package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class KeywordHasNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long keywordHasNewsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    News news;
}
