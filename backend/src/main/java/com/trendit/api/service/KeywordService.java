package com.trendit.api.service;

import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.common.type.PeriodEnum;
import com.trendit.db.repository.CustomRepository;
import com.trendit.db.repository.KeywordNewsRepositorySupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KeywordService {

    private final KeywordNewsRepositorySupport keywordNewsRepositorySupport;
    private final CustomRepository customRepository;

    public List<KeywordNewsData> getKeywordNews(PeriodEnum type) {
        /* TODO: 2개 중 하나만 남기기 */
        //List<KeywordNewsData> keywordNewsList = keywordNewsRepositorySupport.getKeywordNews(type);
        List<KeywordNewsData> keywordNewsList = customRepository.getKeywordNews(type);
        return keywordNewsList;
    }
}
