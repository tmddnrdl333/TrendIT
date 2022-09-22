import { api } from "./axios.js";

async function getNewsApi(success, fail) {
  await api.get("/news/latest").then(success).catch(fail).finally;
}

export { getNewsApi };
