from fastapi import FastAPI

import crawler
import db_util as db
import keyword
import morphological_analysis as morph

app = FastAPI()

@app.get("/internal/run/{date}")
def run(date: str):
    ## 크롤러 실행 및 데이터 DB에 추가
    db.insert_news(crawler.execute_crawler())
    ## headline, news_id 형태소 분석
    analysis_result = morph.morphological_analysis(db.select_news(start_date,end_date))
    keyword.save_as_file(analysis_result)
    ## ssh 접속 및 분석
    # 파일 전송, (hadoop wordcount)분석, 결과 파일 받아야 함

    ## 키워드 판별 및 DB에 데이터 추가
    # 받아온 데이터로 키워드 판별 및 db에 데이터 추가
    # analysis_result : (keyword, news_id) -> (keyword_id, news_id) -> keyword has news
    # 빈도 수도 키워드만 넣어야 함 (keyword.py에 작성해주세요)

    """
    if error -> raise HTTPException(status_code=500, message="Failed")
    현재 예외처리하지 않았으므로, 이후 예외처리 필요함
    """
    return {"message" : "Success"}


## 키워드는 (하루 기준) 일정 빈도 이상 등장한 명사가 키워드가 됨

## 키워드로 등록된 단어의 경우, 빈도를 확인하지 않고 insert
## 키워드로 등록되지 않은 단어의 경우, 당일 빈도를 확인하고, 일정 빈도 이상만 키워드로 등록하고, insert

# 1. 키워드로 등록되어 있는가 -> keyword 테이블에 있음
# 2-1. 등록되어 있는 단어는 빈도 확인 없이 바로 추가
# 2-2. 등록되어 있지 않은 단어
#     1) 키워드의 조건에 맞는지 확인
#     2-1) 빈도가 기준 이상이면 keyword 테이블에 추가
#     2-2) 빈도가 기준 이하면 keyword 테이블에 추가하지 않고 다음 키워드로 넘어감
# 3. 키워드인 단어는 new_id를 keyword has news에 추가해서 가져올 수 있도록 해야함
# 4. 키워드인 단어의 당일 빈도를 db에 저장


