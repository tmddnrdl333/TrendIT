class NormalKeywordReq {
  constructor(keyword) {
    this.keyword = keyword;
  }
}

class CompanyKeywordReq {
  constructor(
    companyName,
    companyCategory,
    companyRepresentative,
    companyLink
  ) {
    this.companyName = companyName;
    this.companyCategory = companyCategory;
    this.companyRepresentative = companyRepresentative;
    this.companyLink = companyLink;
  }
}

export { NormalKeywordReq, CompanyKeywordReq };
