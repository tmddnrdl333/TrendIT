from konlpy.tag import Komoran

def addKeyword():
    data= []
    while True:
        line = input()
        if line == "" : break
        data.append(line)

    user_dict_file = open("../user_data/user_dictionary.txt", 'a', encoding='utf8')
    for line in data:
        user_dict_file.write(line + "\tNNP\n")
    user_dict_file.close()

def morpho_analysis():
    komoran = Komoran(userdic="../user_data/user_dictionary.txt")

    while True:
        line = input()
        if line == "": break
        print(komoran.nouns(line))

while True:
    print("1. 사용자 사전 단어 추가\n2. 형태소 분석\n3. 종료")
    line = input()
    if line == "": break
    elif line == "1": addKeyword()
    elif line == "2": morpho_analysis()
    elif line == "3": break