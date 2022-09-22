import { api } from "./axios.js";

async function dateNewsAgencySearchApi(newsDate, newsAgency, success, fail) {
  // await api.get("/news/latest").then(success).catch(fail).finally;
  console.log(newsDate, newsAgency, success, fail);
}

export { dateNewsAgencySearchApi };
