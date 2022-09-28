import { api } from "./axios.js";

async function getKeywordList(param, success, fail) {
  await api
    .get("/keyword", { params: { keyword: param } })
    .then(success)
    .catch(fail);
}

export { getKeywordList };
