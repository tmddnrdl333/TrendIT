from fastapi import FastAPI

app = FastAPI()

@app.get("/internal/run/{date}")
def run(date: str):
    """
    여기에 크롤러실행, 형태소 분석기 실행, ssh 관련 내용 넣기
    if error -> raise HTTPException(status_code=500, message="Failed")

    """
    return {"message" : "Success"}
