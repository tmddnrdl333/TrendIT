package com.trendit.db.repository;

import com.trendit.db.entity.RecentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RecentNewsRepository extends JpaRepository<RecentNews, Long> {
    String insertQuery = "insert into recent_news(news_id, keyword_id) " +
            "select max(kn.news_id) as news_id, kn.keyword_id as keyword_id " +
            "from keyword_has_news kn " +
            "group by keyword_id;";

    @Transactional
    @Modifying
    @Query(value = "truncate recent_news", nativeQuery = true)
    void truncateRecentNews();

    @Transactional
    @Modifying
    @Query(value = insertQuery, nativeQuery=true)
    int cacheRecentNews();
}
