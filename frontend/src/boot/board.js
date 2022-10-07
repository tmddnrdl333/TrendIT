import { api } from "./axios.js";

function postBoard(data, success, fail) {
  api.post("/board", data).then(success).catch(fail);
}

function updateBoard(data, success, fail) {
  api.put("/board", data).then(success).catch(fail);
}

function deleteBoard(boardId, password, success, fail) {
  api
    .delete(`/board/${boardId}`, { headers: { Authorization: password } })
    .then(success)
    .catch(fail);
}

function getBoards(keywordId, success, fail, boardId) {
  if (boardId != undefined) {
    api
      .get(`/board/keyword/${keywordId}/last-board-id/${boardId}`)
      .then(success)
      .catch(fail);
  } else {
    api.get(`/board/keyword/${keywordId}`).then(success).catch(fail);
  }
}

export { postBoard, updateBoard, deleteBoard, getBoards };
