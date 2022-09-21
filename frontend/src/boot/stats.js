import { api } from "./axios.js";

async function mainChartApi(param, success, fail) {
  await api
    .get("/stats/bar-chart/" + param.type + "/" + param.val)
    .then(success)
    .catch(fail);
}

// company bar-chart에 대한 api는 따로 만든 게 아닌가?
async function companyChartApi(param, success, fail) {
  // ??
  await console.log("hi");
}

async function keywordChartApi(param, success, fail) {
  await api
    .get("/stats/line-chart/" + param.type + "/" + param.keyword)
    .then(success)
    .catch(fail);
}

export { mainChartApi, companyChartApi, keywordChartApi };