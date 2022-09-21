package com.trendit.api.response;

import com.trendit.api.response.data.CompanyData;
import com.trendit.common.model.response.BaseRes;
import com.trendit.db.entity.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanyListGetRes extends BaseRes {

    List<CompanyData> data;

    public static CompanyListGetRes of(int statusCode, String message, List<Company> data) {
        CompanyListGetRes companyListGetRes = new CompanyListGetRes();
        companyListGetRes.setStatusCode(statusCode);
        companyListGetRes.setMessage(message);
        companyListGetRes.setData(new ArrayList<>());
        for(Company company: data){
            companyListGetRes.getData()
                    .add(CompanyData.builder()
                            .companyId(company.getCompanyId())
                            .companyName(company.getCompanyName())
                            .companyCategory(company.getCompanyCategory())
                            .companyMainItem(company.getCompanyMainItem())
                            .companyLink(company.getCompanyLink())
                            .build()
                    );
        }

        return companyListGetRes;
    }
}
