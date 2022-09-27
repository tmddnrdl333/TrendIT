import { api } from "./axios.js";

async function mainChartApi(param, success, fail) {
  await api
    .get("/stats/bar-chart/" + param.type + "/" + param.val)
    .then(success)
    .catch(fail);
}

// company bar-chart에 대한 api는 따로 만든 게 아닌가?
async function companyChartApi(param, success, fail) {
  await api
    .get("/stats/bar-chart/company/" + param)
    .then(success)
    .catch(fail);
}

async function keywordChartApi(param, success, fail) {
  await api
    .get("/stats/line-chart/" + param.type + "/" + param.keyword)
    .then(success)
    .catch(fail);
}

async function getNewsCountApi(success, fail) {
  await api.get("/stats/news-count").then(success).catch(fail);
}

async function getWordcloudApi(param, success, fail) {
  await api
    .get("/stats/wordcloud/" + param)
    .then(success)
    .catch(fail);
}

export {
  mainChartApi,
  companyChartApi,
  keywordChartApi,
  getNewsCountApi,
  getWordcloudApi,
};
