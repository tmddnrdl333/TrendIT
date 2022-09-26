def add_keyword(keyword):
    f = open("user_data/user_dictionary.txt", 'a', encoding='utf8')
    f.write(keyword + "\tNNP\n")
    f.close()
