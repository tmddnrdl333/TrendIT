import { api } from "./axios.js";

async function companyListApi(success, fail) {
  await api.get("/company/list").then(success).catch(fail);
}

async function searchCompanyApi(param, success, fail) {
  await api
    .get("/company/search/", {
      params: { companyName: param.companyName, page: param.page, size: 12 },
    })
    .then(success)
    .catch(fail);
}

export { companyListApi, searchCompanyApi };
