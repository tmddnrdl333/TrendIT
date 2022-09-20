
# output = keyword keyword keyword...
def save_as_file(data, start_date, end_date) :
    filename = start_date + "_" + end_date + " keywords.txt"
    f = open("./keywords/"+filename, 'w', encoding='utf8')
    for line in data :
        f.write(line[0] + " ")
    f.close()