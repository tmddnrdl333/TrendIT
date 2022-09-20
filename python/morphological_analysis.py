from konlpy.tag import Komoran

# data = ((headline, news_id) ,(headline, news_id) ,(headline, news_id) ,(headline, news_id))
# output = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]
def morphological_analysis(data) :
    morpho_analysis_result = []
    komoran = Komoran()
    for item in data :
        pos = komoran.nouns(item[0])
        for keyword in pos :
            morpho_analysis_result.append(keyword, item[1])
    return morpho_analysis_result

## 키워드 테이블에 키워드 저장되어 있는지 확인
## 키워드는 하루 기준 일정 빈도 이상 등장한 키워드
## 등록된 키워드의 경우, 빈도를 확인하지 않고 insert
## 등록되지 않은 키워드는 당일 빈도를 확인하고, 일정 빈도 이상만 키워드로 등록하고, insert
 