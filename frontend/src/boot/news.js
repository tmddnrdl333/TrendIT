import { api } from "./axios.js";

async function getNewsApi(params, success, fail) {
  await api
    .get("/news/latest", {
      params,
    })
    .then(success)
    .catch(fail).finally;
}

async function getNewsByOptionsApi(params, success, fail) {
  params = {
    newsDate: params.newsDate,
    newsAgency: params.selection, // 언론사
    page: params.page,
    size: params.size,
  };

  await api.get("/news/latest", { params }).then(success).catch(fail).finally;
}

async function searchApi(param, success, fail) {
  await api
    .get("/news", {
      params: {
        keywordId: param.keywordId,
        newsDate: param.period,
        page: param.page,
        size: 12,
      },
    })
    .then(success)
    .catch(fail);
}

export { getNewsApi, getNewsByOptionsApi, searchApi };
