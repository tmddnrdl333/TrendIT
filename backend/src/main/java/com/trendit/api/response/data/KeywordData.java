package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class KeywordData {
    long keywordId;
    String keyword;
    long companyId;
}
