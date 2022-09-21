import db_util as db

KEYWORD_COUNT_STANDARD = 10


# output = keyword keyword keyword...
def save_as_file(data, start_date, end_date) :
    filename = start_date + "_" + end_date + " keywords.txt"
    f = open("./keywords/"+filename, 'w', encoding='utf8')
    for line in data :
        f.write(line[0] + " ")
    f.close()


def hadoop_result_from_file(data) :
    f = open("")
    f.close()

# hadoop_result = [(keyword, count), (keyword, count), (keyword, count)]
# analysis_result = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]
# date = "2022-09-20"
def extract_keyword(hadoop_result, analysis_result, date) :
    keywords = dict()
    for item in hadoop_result :
        # 키워드 등록 여부 확인
        keyword_id = db.is_keyword(item[0])
        # 등록되지 않은 단어 
        if keyword_id == null :
            # 키워드 조건 확인
            if item[1] >= KEYWORD_COUNT_STANDARD :  
                # 영단어 중복 없애기 위해 대문자 데이터만 삽입
                keywords[item[0].upper()] = 0
        # 등록된 단어
        else :
            keywords[item[0].upper()] = keyword_id

    new_keywords = [k for k, v in keywords.items() if v == 0]

    # 키워드 새로 등록
    db.insert_keyword(new_keywords)
    # 키워드 : 키워드 id 형식의 dict 완성
    keywords = update_keyword_id(keywords)

def update_keyword_id(keywords):
    for k, v in keywords.items() :
        if v == 0 :
            keywords[k] = db.is_keyword(k)
    return keywords


def save_statistics(keywords, hadoop_result, date):
    date = date.split("-")
    statistics = [(keywords[keyword], frequency, date[0], date[1], date[2]) for (keyword, frequency) in hadoop_result if keyword in keywords.keys()]
    # (키워드 id, 빈도수, 날짜) 등록
    db.insert_statistics_date(statistics)

def save_keyword_news(keywords, analysis_result):
    keyword_news = [(keywords[keyword], news_id) for (keyword, news_id) in analysis_result if keyword in keywords.keys()]
    # (키워드 id, news id) 등록
    db.insert_keyword_has_news(keyword_news)