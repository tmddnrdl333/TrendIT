package com.trendit.api.service;

import com.trendit.api.response.KeywordNewsGetRes;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.db.repository.KeywordNewsRepositorySupport;
import com.trendit.db.repository.KeywordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KeywordService {

    private final KeywordNewsRepositorySupport keywordNewsRepositorySupport;

    public List<KeywordNewsData> getKeywordNews(String type) {
        List<KeywordNewsData> keywordNewsList = keywordNewsRepositorySupport.getKeywordNews(type);
        return keywordNewsList;
    }
}
