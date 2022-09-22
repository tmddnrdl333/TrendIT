import db_util as db

KEYWORD_COUNT_STANDARD = 10


# output = keyword keyword keyword...
def save_as_file(data, date) :
    filename = date + "_keywords.txt"
    f = open("./keywords/"+filename, 'w', encoding='utf8')
    for line in data :
        f.write(line[0] + " ")
    f.close()


def hadoop_result_from_file(data) :
    f = open("")
    f.close()

# hadoop_result = [(keyword, count), (keyword, count), (keyword, count)]
# analysis_result = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]
def extract_keyword(hadoop_result) :
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
    return keywords


def update_keyword_id(keywords):
    for k, v in keywords.items() :
        if v == 0 :
            keywords[k] = db.is_keyword(k)
    return keywords


# date = "2022-09-20"
def save_statistics(keywords, hadoop_result, date):
    date = date.split("-")
    statistics = [(keywords[keyword], frequency, date[0], date[1], date[2]) for (keyword, frequency) in hadoop_result if keyword in keywords.keys()]
    # (키워드 id, 빈도수, 날짜) 등록
    db.insert_statistics_date(statistics)


def save_keyword_news(keywords, analysis_result):
    keyword_news = [(keywords[keyword], news_id) for (keyword, news_id) in analysis_result if keyword in keywords.keys()]
    # (키워드 id, news id) 등록
    db.insert_keyword_has_news(keyword_news)




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