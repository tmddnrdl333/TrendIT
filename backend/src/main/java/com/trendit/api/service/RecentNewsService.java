package com.trendit.api.service;

import com.trendit.db.repository.RecentNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecentNewsService {
    private final RecentNewsRepository recentNewsRepository;


    public void truncateRecentNews() {
        recentNewsRepository.truncateRecentNews();
    }

    public void cacheRecentNews() {recentNewsRepository.cacheRecentNews(); }
}
