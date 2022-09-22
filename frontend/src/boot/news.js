import { api } from "./axios.js";

async function getNewsApi(success, fail) {
  await api.get("/news/latest").then(success).catch(fail).finally;
}

async function dateNewsAgencySearchApi(newsDate, newsAgency, success, fail) {
  // await api.get("/news/latest").then(success).catch(fail).finally;
  console.log(newsDate, newsAgency, success, fail);
}

async function searchApi(param, success, fail) {
  await api
    .get("/news", {
      params: {
        keyword: param.keyword,
        period: param.period,
        page: param.page,
      },
    })
    .then(success)
    .catch(fail);
}

export { getNewsApi, dateNewsAgencySearchApi, searchApi };
