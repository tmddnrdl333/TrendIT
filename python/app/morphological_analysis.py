from konlpy.tag import Komoran

# data = ((headline, news_id) ,(headline, news_id) ,(headline, news_id) ,(headline, news_id))
# output = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]
def morphological_analysis(data) :
    morpho_analysis_result = []
    komoran = Komoran(userdic="user_data/user_dictionary.txt")
    for item in data :
        pos = komoran.nouns(item[0])
        for keyword in pos :
            morpho_analysis_result.append(tuple([keyword, item[1]]))
    return morpho_analysis_result