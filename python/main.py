from fastapi import FastAPI, HTTPException

import crawler
import db_util as db
import keyword_processing as keyproc
import morphological_analysis as morph
import subprocess

app = FastAPI()

mutex = 1

@app.get("/internal/run/{date}")
def run(date: str):

    global mutex

    ## 크롤러 실행 및 데이터 DB에 추가
    db.insert_news(crawler.execute_crawler())

    ## headline, news_id 형태소 분석
    analysis_result = morph.morphological_analysis(db.select_news(date))
    keyproc.save_as_file(analysis_result, date)

    ## ssh 접속 및 분석
    # 파일 전송, (hadoop wordcount)분석, 결과 파일 받아야 함
    # 결과 파일은 hadoop_result = [(keyword, count), (keyword, count), (keyword, count)] 형태로 변경해서 넘겨야 함

    while (mutex == 0):
        pass

    mutex = 0

    result = subprocess.call(['sh', '/code/script.sh'])

    mutex = 1

    if (result != 0):
        raise HTTPException(status_code = 500, detail = "Failed")

    ## 키워드 판별 및 DB에 데이터 추가
    keywords = keyproc.extract_keyword(hadoop_result)
    keyproc.save_keyword_news(keywords, analysis_result)
    keyproc.save_statistics(keywords, hadoop_result, date)

    """
    if error -> raise HTTPException(status_code=500, detail="Failed")
    현재 예외처리하지 않았으므로, 이후 예외처리 필요함
    """
    
    return {"message" : "Success"}