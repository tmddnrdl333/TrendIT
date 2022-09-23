import db_util as db
testkeywords = ['abc', 'db', 'dd']
new_keywords = ['개발', '개최', '경찰', '과기', '글로벌', '대', '대비', '대응', '대한민국', '더블', '도입', '디지털', '문자', '삼성', '삼성전자', '세계', '스마트', '온라인', '유럽', '일', '정보', '추석', '출시', '통신', '통합', '간', '고객', '교육', '기술', '명', '비상', '서비스', '수사', '시장', '유튜브', '인증', '정부', '채널', '태풍', '폰', '폴', '해킹', '확대']


# new_keywords_tuple_test = [tuple([item]) for item in new_keywords]
# print(new_keywords_tuple_test)
#
# test = list(map(tuple, map(list, testkeywords)))
# abc = list(map(list, testkeywords))
# print(test)
# print(testkeywords)
# print(abc)

new_keywords_tuple = [tuple([item]) for item in new_keywords]
print(new_keywords_tuple)


print(db.insert_keyword(new_keywords_tuple))