import { api } from "./axios.js";

// async?? await?? 붙여야되나?

function postBoard(data, success, fail) {
  console.log(data);
  api.post("/board", data).then(success).catch(fail);
}

function updateBoard(data, success, fail) {
  api.put("/board", data).then(success).catch(fail);
}

// Authorization : "dqkwodq1213" 헤더 추가
function deleteBoard(boardId, password, success, fail) {
  api
    .delete(`/board/${boardId}`, { headers: { Authorization: password } })
    .then(success)
    .catch(fail);
}

// function getBoards(keywordId, boardId, success, fail) {
//   api.get(`/board/keyword/${keywordId}`).then(success).catch(fail);
// }

function getBoards(keywordId, success, fail, boardId) {
  if (boardId != undefined) {
    console.log("boardID OO", boardId);
    api
      .get(`/board/keyword/${keywordId}/last-board-id/${boardId}`)
      .then(success)
      .catch(fail);
  } else {
    console.log("boardID XX", boardId);
    api.get(`/board/keyword/${keywordId}`).then(success).catch(fail);
  }
}

export { postBoard, updateBoard, deleteBoard, getBoards };
