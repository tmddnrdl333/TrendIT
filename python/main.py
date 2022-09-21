from fastapi import FastAPI

import crawler
import db_util as db
import keyword_processing as keyproc
import morphological_analysis as morph

app = FastAPI()

@app.get("/internal/run/{date}")
def run(date: str):
    ## 크롤러 실행 및 데이터 DB에 추가
    db.insert_news(crawler.execute_crawler())

    ## headline, news_id 형태소 분석
    analysis_result = morph.morphological_analysis(db.select_news(date))
    keyword_processing.save_as_file(analysis_result)

    ## ssh 접속 및 분석
    # 파일 전송, (hadoop wordcount)분석, 결과 파일 받아야 함
    # 결과 파일은 hadoop_result = [(keyword, count), (keyword, count), (keyword, count)] 형태로 변경해서 넘겨야 함

    ## 키워드 판별 및 DB에 데이터 추가
    keywords = keyproc.extract_keyword(hadoop_result)
    keyproc.save_keyword_news(keywords, analysis_result)
    keyproc.save_statistics(keywords, hadoop_result, date)

    """
    if error -> raise HTTPException(status_code=500, message="Failed")
    현재 예외처리하지 않았으므로, 이후 예외처리 필요함
    """
    
    return {"message" : "Success"}