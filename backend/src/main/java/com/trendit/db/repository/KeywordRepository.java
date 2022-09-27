package com.trendit.db.repository;

import com.trendit.db.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByKeyword(String keyword);
}
