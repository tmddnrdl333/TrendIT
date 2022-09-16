import { api } from "./axios.js";

async function testApi(success, fail) {
  await api.get("/stats/wordcloud/day").then(success).catch(fail);
}

export { testApi };
