import { api } from "./axios.js";

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

export { searchApi };
