data= []
while True:
    line = input()
    if(line == ""): break
    data.append(line)

user_dict_file = open("../user_data/user_dictionary.txt", 'a', encoding='utf8')
for line in data:
    user_dict_file.write(line + "\tNNP\n")
user_dict_file.close()