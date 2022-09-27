package com.trendit.api.service;

import com.trendit.api.exception.DuplicatedKeywordException;
import com.trendit.api.exception.PasswordMisMatchException;
import com.trendit.api.request.KeywordCompanyPostReq;
import com.trendit.api.request.KeywordPostReq;
import com.trendit.api.response.data.KeywordData;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.common.type.PeriodEnum;
import com.trendit.db.entity.Board;
import com.trendit.db.entity.Company;
import com.trendit.db.entity.Keyword;
import com.trendit.db.repository.CustomRepository;
import com.trendit.db.repository.KeywordNewsRepositorySupport;
import com.trendit.db.repository.KeywordRepository;
import com.trendit.db.repository.KeywordRepositorySupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class KeywordService {

    private final WebClient webClient;

    private final KeywordNewsRepositorySupport keywordNewsRepositorySupport;
    private  final KeywordRepositorySupport keywordRepositorySupport;
    private final CustomRepository customRepository;
    private final KeywordRepository keywordRepository;

    public List<KeywordNewsData> getKeywordNews(PeriodEnum type) {
        /* TODO: 2개 중 하나만 남기기 */
        //List<KeywordNewsData> keywordNewsList = keywordNewsRepositorySupport.getKeywordNews(type);
        List<KeywordNewsData> keywordNewsList = customRepository.getKeywordNews(type);
        return keywordNewsList;
    }

    public void addKeyword(KeywordPostReq keywordPostReq) throws DuplicatedKeywordException, Exception {
        if (duplicatedKeyword(keywordPostReq.getKeyword())) {
            throw new DuplicatedKeywordException("Dupllicated keyword");
        } else {
            keywordRepository.save(toKeywordEntity(keywordPostReq.getKeyword(), null));
        }
    }

//    public void addKeywordCompany(KeywordCompanyPostReq keywordcompanyPostReq) throws DuplicatedKeywordException {
//        if (duplicatedKeyword(keywordcompanyPostReq.getKeyword())) {
//            throw new DuplicatedKeywordException("Dupllicated keyword");
//        } else {
//            // company save
//            // company를 가져와서 keyword에 함께 넣기
//            keywordRepository.save(keywordPostReq.toKeywordEntity(null));
//        }
//    }

    public Keyword toKeywordEntity (String keyword, Company company) {
        return Keyword.builder()
                .keyword(keyword)
                .company(company)
                .build();
    }

    public boolean duplicatedKeyword(String keyword) {
        List<Keyword> keywordList = keywordRepository.findByKeyword(keyword);
        if (keywordList.size() > 0) return true;
        else return false;
    }

    public List<KeywordData> getKeywordList(String keyword) {
        List<KeywordData> keywordList = keywordRepositorySupport.getKeywordList(keyword);
        return keywordList;
    }
}
