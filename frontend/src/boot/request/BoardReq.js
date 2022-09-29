class BoardPostReq {
  constructor(keywordId, userName, password, boardContent) {
    this.keywordId = keywordId;
    this.userName = userName;
    this.password = password;
    this.boardContent = boardContent;
  }
}

class BoardUpdateReq {
  constructor(boardId, password, boardContent) {
    this.boardId = boardId;
    this.password = password;
    this.boardContent = boardContent;
  }
}

export { BoardPostReq, BoardUpdateReq };
