package com.trendit.api.service;

import com.trendit.api.response.KeywordNewsGetRes;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.db.repository.CustomRepository;
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
    private final CustomRepository customRepository;

    public List<KeywordNewsData> getKeywordNews(String type) {
        //List<KeywordNewsData> keywordNewsList = keywordNewsRepositorySupport.getKeywordNews(type);
        List<KeywordNewsData> keywordNewsList = customRepository.getKeywordNews(type);
        return keywordNewsList;
    }
}
