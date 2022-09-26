from fastapi import FastAPI, HTTPException

from app import keyword_processing as keyproc, crawler, db_util as db, morphological_analysis as morph, user_dictionary as udic
import subprocess

app = FastAPI()

hadoop_mutex = 1
reducer = 2

@app.get("/internal/run/{date}")
def run(date: str):
    global hadoop_mutex

    ## 크롤러 실행 및 데이터 DB에 추가
    db.insert_news(crawler.execute_crawler(date))

    ## headline, news_id 형태소 분석
    analysis_result = morph.morphological_analysis(db.select_news(date))
    # print(analysis_result)
    keyproc.save_as_file(analysis_result)

    ## ssh 접속 및 분석
    while (hadoop_mutex == 0): pass
    hadoop_mutex = 0
    result = subprocess.call(['sh', '/code/script.sh'])
    hadoop_mutex = 1
    if (result != 0):
        raise HTTPException(status_code=500, detail="Failed")
    hadoop_result = keyproc.get_hadoop_result(reducer)

    ## 키워드 판별 및 DB에 데이터 추가
    keywords = keyproc.extract_keyword(hadoop_result)
    keyproc.save_keyword_news(keywords, analysis_result)
    keyproc.save_statistics(keywords, hadoop_result, date)

    """
    if error -> raise HTTPException(status_code=500, detail="Failed")
    현재 예외처리하지 않았으므로, 이후 예외처리 필요함
    """
    
    return {"message" : "Success"}


@app.get("/internal/add_user_dictionary/{keyword}")
def add_user_dictionary(keyword: str):
    try:
        udic.add_keyword(keyword)
    except:
        raise HTTPException(status_code=500, detail="Failed")
    return {"message": "Success"}