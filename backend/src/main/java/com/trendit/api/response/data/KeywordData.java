package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class KeywordData {
    long keywordId;
    String keyword;
    long companyId;
}
