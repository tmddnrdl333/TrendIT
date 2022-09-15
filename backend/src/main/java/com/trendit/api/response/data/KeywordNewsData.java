package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class KeywordNewsData {
 String keyword;
 int count;
 NewsData newsData;
}
