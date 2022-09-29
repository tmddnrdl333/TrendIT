package com.trendit.api.service;

import com.trendit.api.exception.DuplicatedKeywordException;
import com.trendit.api.exception.KeywordHasSpaceException;
import com.trendit.api.request.KeywordCompanyPostReq;
import com.trendit.api.request.KeywordPostReq;
import com.trendit.api.response.data.KeywordData;
import com.trendit.api.response.data.KeywordNewsData;
import com.trendit.common.type.PeriodEnum;
import com.trendit.common.util.StringUtils;
import com.trendit.db.entity.Company;
import com.trendit.db.entity.Keyword;
import com.trendit.db.repository.*;
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
    private final CompanyRepository companyRepository;

    public List<KeywordNewsData> getKeywordNews(PeriodEnum type) {
        /* TODO: 2개 중 하나만 남기기 */
        //List<KeywordNewsData> keywordNewsList = keywordNewsRepositorySupport.getKeywordNews(type);
        List<KeywordNewsData> keywordNewsList = customRepository.getKeywordNews(type);
        return keywordNewsList;
    }

    public void addKeyword(KeywordPostReq keywordPostReq) throws DuplicatedKeywordException, Exception {
        if (!StringUtils.isValidKeyword(keywordPostReq.getKeyword())) {
            throw new KeywordHasSpaceException("Keyword has space");
        }
        if (duplicatedKeyword(keywordPostReq.getKeyword())) {
            throw new DuplicatedKeywordException("Duplicated keyword");
        } else {
            keywordRepository.save(toKeywordEntity(keywordPostReq.getKeyword(), null));
        }
    }

    public void addKeywordCompany(KeywordCompanyPostReq keywordCompanyPostReq) throws DuplicatedKeywordException, Exception {
        if (!StringUtils.isValidKeyword(keywordCompanyPostReq.getCompanyName())) {
            throw new KeywordHasSpaceException("Keyword has space");
        }
        if (duplicatedKeyword(keywordCompanyPostReq.getCompanyName())) {
            throw new DuplicatedKeywordException("Duplicated keyword");
        } else {
            Company targetCompany = companyRepository.save(Company.builder()
                    .companyName(keywordCompanyPostReq.getCompanyName())
                    .companyCategory(keywordCompanyPostReq.getCompanyCategory())
                    .companyRepresentative(keywordCompanyPostReq.getCompanyRepresentative())
                    .companyLink(keywordCompanyPostReq.getCompanyLink())
                    .build());
            keywordRepository.save(toKeywordEntity(keywordCompanyPostReq.getCompanyName(), targetCompany));
        }
    }

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
