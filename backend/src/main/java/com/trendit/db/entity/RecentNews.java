package com.trendit.db.entity;

import javax.persistence.*;

@Entity
public class RecentNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long recentNewsId;

    @ManyToOne()
    @JoinColumn(name = "keyword_id")
    Keyword keyword;

    @ManyToOne()
    @JoinColumn(name = "news_id")
    News news;
}
