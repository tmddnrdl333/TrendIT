from konlpy.tag import Komoran

# data = ((headline, news_id) ,(headline, news_id) ,(headline, news_id) ,(headline, news_id))
# output = [(keyword, news_id), (keyword, news_id), (keyword, news_id)]
def morphological_analysis(data) :
    morpho_analysis_result = []
    komoran = Komoran(userdic="user_data/user_dictionary.txt")
    for item in data :
        komoran_result = komoran.pos(item[0])
        for keyword in komoran_result :
            if keyword[1] in ('NNP', 'NNG', 'SL') :
                morpho_analysis_result.append(tuple([keyword[0].replace(" ", ""), item[1]]))
    return morpho_analysis_result