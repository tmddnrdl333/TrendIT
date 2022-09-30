import { api } from "./axios.js";

async function getKeywordListApi(param, success, fail) {
  await api
    .get("/keyword", { params: { keyword: param } })
    .then(success)
    .catch(fail);
}

async function createNormalKeywordApi(data, success, fail) {
  await api.post("/keyword", data).then(success).catch(fail);
}

// async function createNormalKeywordApi(param, success, fail) {
//   await api
//     .post("/keyword", { params: { keyword: param } })
//     .then(success)
//     .catch(fail);
// }

async function createCompanyKeywordApi(data, success, fail) {
  await api.post("/keyword/company", data).then(success).catch(fail);
}

async function getKeywordApi(param, success, fail) {
  await api
    .get("/keyword/" + param)
    .then(success)
    .catch(fail);
}

export {
  getKeywordListApi,
  createNormalKeywordApi,
  createCompanyKeywordApi,
  getKeywordApi,
};
