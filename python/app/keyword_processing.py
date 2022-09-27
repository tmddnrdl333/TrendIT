from app import db_util as db
from app import date_util as date_util

KEYWORD_COUNT_STANDARD = 10

# output = keyword keyword keyword...
def save_as_file(data) :
    filename = "keyword_input.in"
    f = open("keywords_input/"+filename, 'w', encoding='utf8')
    for line in data :
        f.write(line[0] + " ")
    f.close()


def get_hadoop_result(reducer) :
    hadoop_result = []
    for i in range(reducer) :
        f = open("keywords_output/" + "keyword_output" + str(i+1) + ".out", 'r')
        while True:
            line = f.readline()
            if not line: break
            line_split = line.split()
            hadoop_result.append(tuple([str(line_split[0]), int(line_split[1])]))
        f.close()
    return hadoop_result


# hadoop_result = [(keyword, count), (keyword, count), (keyword, count)]
# analysis_result = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]

def extract_keyword(hadoop_result) :
    keywords = dict()
    for item in hadoop_result :
        # 키워드 등록 여부 확인
        keyword_id = db.is_keyword(item[0])
        # 등록되지 않은 단어
        if len(keyword_id) == 0:
            # 키워드 조건 확인
            if item[1] >= KEYWORD_COUNT_STANDARD :  
                # 영단어 중복 없애기 위해 대문자 데이터만 삽입
                keywords[item[0].upper()] = 0
        # 등록된 단어
        else :
            keywords[item[0].upper()] = keyword_id[0]

    new_keywords = [tuple([k]) for k, v in keywords.items() if v == 0]

    # 키워드 새로 등록
    db.insert_keyword(new_keywords)
    # 키워드 : 키워드 id 형식의 dict 완성
    keywords = update_keyword_id(keywords)

    return keywords


def update_keyword_id(keywords):
    for k, v in keywords.items() :
        if v == 0 :
            keywords[k] = db.is_keyword(k)[0]
    return keywords


# date = "2022-09-20"
def save_statistics(keywords, hadoop_result, date):

    statistics = [(keywords[keyword], frequency, date) for (keyword, frequency) in hadoop_result if keyword in keywords.keys()]

    # (키워드 id, 빈도수, 날짜) 등록
    db.insert_statistics_date(statistics)

    week_string = date_util.get_first_day_of_week(date)
    month_string = date_util.get_first_day_of_month(date)
    year_string = date_util.get_first_day_of_year(date)

    # hadoop result로 온 키워드에 대해서 각각의 keyword_id를 구하고 keyword_id와 date로 조회해서 각각이 week 테이블에 있는지 검사한다.
    # 검사해서 있으면 해당 frequency 값을 가져오고 frequency에 update

    no_exists_list = []
    exists_list = []

    for (keyword, frequency) in hadoop_result:
        if keyword not in keywords.keys():
          continue
        keyword_id = keywords[keyword]
        result_set = db.get_statistics_week(week_string, keyword_id)
        if len(result_set) == 0:
            no_exists_list.append((keyword_id, frequency, week_string))
        else:
            s_id = result_set[0][0]
            new_frequency = frequency + result_set[0][1]
            exists_list.append((new_frequency, s_id))
    
    db.insert_statistics_week(no_exists_list)
    db.update_statistics_week(exists_list)

    no_exists_list = []
    exists_list = []

    for (keyword, frequency) in hadoop_result:
        if keyword not in keywords.keys():
          continue
        keyword_id = keywords[keyword]
        result_set = db.get_statistics_month(month_string, keyword_id)
        if len(result_set) == 0:
            no_exists_list.append((keyword_id, frequency, month_string))
        else:
            s_id = result_set[0][0]
            new_frequency = frequency + result_set[0][1]
            exists_list.append((new_frequency, s_id))
    
    db.insert_statistics_month(no_exists_list)
    db.update_statistics_month(exists_list)

    no_exists_list = []
    exists_list = []

    for (keyword, frequency) in hadoop_result:
        if keyword not in keywords.keys():
          continue
        keyword_id = keywords[keyword]
        result_set = db.get_statistics_year(year_string, keyword_id)
        if len(result_set) == 0:
            no_exists_list.append((keyword_id, frequency, year_string))
        else:
            s_id = result_set[0][0]
            new_frequency = frequency + result_set[0][1]
            exists_list.append((new_frequency, s_id))
    
    db.insert_statistics_year(no_exists_list)
    db.update_statistics_year(exists_list)


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