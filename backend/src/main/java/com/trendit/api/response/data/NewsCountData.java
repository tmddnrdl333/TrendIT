package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsCountData {
    long todayCount;
    long totalCount;
}