package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class KeywordNewsData {
 String keyword;
 Long keywordId;
 int count;
 NewsData newsData;
}
