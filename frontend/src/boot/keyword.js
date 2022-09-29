import { api } from "./axios.js";

async function getKeywordListApi(param, success, fail) {
  await api
    .get("/keyword", { params: { keyword: param } })
    .then(success)
    .catch(fail);
}

async function getKeywordApi(param, success, fail) {
  await api
    .get("/keyword/" + param)
    .then(success)
    .catch(fail);
}

export { getKeywordListApi, getKeywordApi };
