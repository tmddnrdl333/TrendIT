package com.trendit.api.response;

import com.trendit.api.response.data.CompanyData;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Getter
@Setter
public class SearchCompanyGetRes extends BaseRes {

    Page<CompanyData> data;

    public static SearchCompanyGetRes of(int statusCode, String msg, Page<Company> companyPage) {
        SearchCompanyGetRes searchCompanyGetRes = new SearchCompanyGetRes();
        searchCompanyGetRes.setStatusCode(statusCode);
        searchCompanyGetRes.setMessage(msg);
        searchCompanyGetRes.setData(
                companyPage.map(m -> CompanyData
                        .builder()
                        .companyId(m.getCompanyId())
                        .companyName(m.getCompanyName())
                        .companyLink(m.getCompanyLink())
                        .companyCategory(m.getCompanyCategory())
                        .companyRepresentative(m.getCompanyRepresentative())
                        .build())
        );

        return searchCompanyGetRes;
    }
}
