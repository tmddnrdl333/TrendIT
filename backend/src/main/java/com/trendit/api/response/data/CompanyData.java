package com.trendit.api.response.data;

import com.trendit.db.entity.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class CompanyData {
    long companyId;
    String companyName;
    String companyCategory;
    String companyMainItem;
    String companyLink;
    Keyword keyword;
}
