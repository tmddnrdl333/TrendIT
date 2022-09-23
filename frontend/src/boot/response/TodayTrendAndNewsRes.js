import { NewsRes } from "./NewsRes.js";

class TodayTrendAndNewsRes {
  getText() {
    return this.keyword;
  }

  getSize() {
    return this.count;
  }
  constructor(keyword, count, selectedNews) {
    this.keyword = keyword;
    this.count = count;

    this.selectedNews = new NewsRes(
      selectedNews.newsId,
      selectedNews.headline,
      selectedNews.newsContent,
      selectedNews.newsDate,
      selectedNews.newsAgency,
      selectedNews.newsLink,
      selectedNews.imgLink
    );
  }
}

export { TodayTrendAndNewsRes };
