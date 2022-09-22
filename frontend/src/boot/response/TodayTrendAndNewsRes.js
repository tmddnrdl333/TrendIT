import { NewsRes } from "NewsRes.js";

class TodayTrendAndNewsRes {
  constructor(keyword, count, selectedNews) {
    this.keyword = keyword;
    this.count = count;

    function getText() {
      return this.keyword;
    }

    function getSize() {
      return this.count;
    }

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
