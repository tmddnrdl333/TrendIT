package com.trendit.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trendit.api.response.data.KeywordData;
import com.trendit.db.entity.Keyword;
import com.trendit.db.entity.QKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class KeywordRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QKeyword qKeyword = QKeyword.keyword1;

    public List<KeywordData> getKeywordList(String keyword) {
        List<Keyword> sql_list = jpaQueryFactory.select(qKeyword)
                .from(qKeyword)
                .where(qKeyword.keyword.like("%"+keyword+"%"))
                .fetch();
        List<KeywordData> ret_list = new ArrayList<>();
        for(Keyword k:sql_list)
            ret_list.add(new KeywordData(k.getKeywordId(),k.getKeyword(),k.getCompany().getCompanyId()));
        return ret_list;
    }

}
