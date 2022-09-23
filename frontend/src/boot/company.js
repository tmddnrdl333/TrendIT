import { api } from "./axios.js";

async function companyListApi(success, fail) {
  await api.get("/company/list").then(success).catch(fail);
}

async function searchCompanyApi(param, success, fail) {
  await api
    .get("/company/search/" + param)
    .then(success)
    .catch(fail);
}

export { companyListApi, searchCompanyApi };
