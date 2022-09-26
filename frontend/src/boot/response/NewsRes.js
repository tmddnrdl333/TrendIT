class NewsRes {
  constructor(
    newsId,
    headline,
    newsContent,
    newsDate,
    newsAgency,
    newsLink,
    imgLink
  ) {
    this.newsId = newsId;
    this.headline = headline;
    this.newsContent = newsContent;
    this.newsDate = newsDate;
    this.newsAgency = newsAgency;
    this.newsLink = newsLink;
    this.imgLink = imgLink;
  }
}
export { NewsRes };
