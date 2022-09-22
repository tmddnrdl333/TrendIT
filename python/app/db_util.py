import pymysql

def execute_select(sql, data):
    conn = pymysql.connect(
        user="trendit",
        passwd="trendit829",
        host="172.26.2.161",
        port=32000,
        db="trendit",
        charset='utf8'
    )
    cursor = conn.cursor()
    cursor.execute(sql, data)
    result = cursor.fetchall()
    cursor.close()
    conn.close()
    return result

def execute_insert_many(sql, data):
    conn = pymysql.connect(
        user="trendit",
        passwd="trendit829",
        host="172.26.2.161",
        port=32000,
        db="trendit",
        charset='utf8'
    )
    cursor = conn.cursor()
    cursor.executemany(sql, data)
    cursor.close()
    conn.close()

# usage
# data = [(headline, img_link, news_agency, news_content, news_date, news_link),
#         (headline, img_link, news_agency, news_content, news_date, news_link),
#         (headline, img_link, news_agency, news_content, news_date, news_link)]
def insert_news(data):
    sql = """INSERT INTO `news`(headline, img_link, news_agency, news_content, news_date, news_link) VALUES (%s, %s, %s, %s, %s, %s);"""
    execute_insert_many(sql, data)

# usage
# date = "2022-01-01"
def select_news(date):
    sql = """SELECT headline, news_id from `news` WHERE news_date = %s ORDER BY news_id;"""
    return execute_select(sql, date)

# usage
# keyword = "keyword"
def is_keyword(keyword):
    sql = """SELECT keyword_id from `keyword` WHERE keyword = %s;"""
    return execute_select(sql, keyword)

# usage
# keywords_input = [keyword, keyword, keyword]
def insert_keyword(keywords) :
    sql = """INSERT INTO `keyword`(keyword) VALUES (%s)"""
    execute_insert_many(sql, list(keywords))

# usage
# data = [(keyword_id, frequency, year, month, day),
#         (keyword_id, frequency, year, month, day)]
def insert_statistics_date(data) :
    sql = """INSERT INTO `statistics_date`(keyword_id, frequeny, year, month, day) VALUES (%s, %s, %s, %s, %s)"""
    execute_insert_many(sql, data)

# usage
# data = [(keyword_id, news_id),
#         (keyword_id, news_id),
#         (keyword_id, news_id)]
def insert_keyword_has_news(data):
    sql = """INSERT INTO `keyword_has_news`(keyword_id, news_id) VALUES (%s, %s);"""
    execute_insert_many(sql, data)