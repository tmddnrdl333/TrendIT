package com.trendit.api.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class NewsData {
    long newsId;
    String headline;
    String newsContent;
    LocalDate newsDate;
    String newsAgency;
    String newsLink;
    String imgLink;
}
