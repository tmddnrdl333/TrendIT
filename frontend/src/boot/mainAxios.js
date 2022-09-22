import { api } from "./axios.js";

async function getNewsCollectData(success, fail) {
  await api.get("/stats/news-count").then(success).catch(fail);
}

export { getNewsCollectData };
