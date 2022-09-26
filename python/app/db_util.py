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
    conn.commit()
    cursor.close()
    conn.close()

def get_connection():
    return pymysql.connect(
        user="trendit",
        passwd="trendit829",
        host="172.26.2.161",
        port=32000,
        db="trendit",
        charset='utf8'
    )

def execute_update(sql, count, target_time):
    conn = get_connection()
    cursor = conn.cursor()
    rows = cursor.exectue(sql, count, target_time)
    conn.commit()
    cursor.close()
    conn.close()
    return rows


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

def get_statistics_week(target_time):
    sql = """SELECT statistics_week_id, frequency from `statistics_week` WHERE target_time = %s;"""
    return execute_select(sql, target_time)

def get_statistics_month(target_time):
    sql = """SELECT statistics_month_id, frequency from `statistics_month` WHERE target_time = %s;"""
    return execute_select(sql, target_time)

def get_statistics_year(target_time):
    sql = """SELECT statistics_year_id, frequency from `statistics_year` WHERE target_time = %s;"""
    return execute_select(sql, target_time)

def update_statistics_week(count, target_time):
    sql = """UPDATE statistics_week SET frequency = %d WHERE target_time = %s;"""
    return execute_update(sql, count, target_time)

def update_statistics_month(count, target_time):
    sql = """UPDATE statistics_month SET frequency = %d WHERE target_time = %s;"""
    return execute_update(sql, count, target_time)

def update_statistics_year(count, target_time):
    sql = """UPDATE statistics_year SET frequency = %d WHERE target_time = %s;"""
    return execute_update(sql, count, target_time)

# usage
# keywords_input = [keyword, keyword, keyword]
def insert_keyword(keywords) :
    sql = """INSERT INTO `keyword`(keyword) VALUES (%s)"""
    rows = execute_insert_many(sql, keywords)
    return rows

# usage
# data = [(keyword_id, frequency, targetTime),
#         (keyword_id, frequency, targetTime)]
def insert_statistics_date(data) :
    sql = """INSERT INTO `statistics_date`(keyword_id, frequency, target_time) VALUES (%s, %s, %s)"""
    execute_insert_many(sql, data)

def insert_statistics_week(data) :
    sql = """INSERT INTO `statistics_week`(keyword_id, frequency, target_time) VALUES (%s, %s, %s)"""
    execute_insert_many(sql, data)

def insert_statistics_month(data) :
    sql = """INSERT INTO `statistics_month`(keyword_id, frequency, target_time) VALUES (%s, %s, %s)"""
    execute_insert_many(sql, data)

def insert_statistics_year(data) :
    sql = """INSERT INTO `statistics_year`(keyword_id, frequency, target_time) VALUES (%s, %s, %s)"""
    execute_insert_many(sql, data)

# usage
# data = [(keyword_id, news_id),
#         (keyword_id, news_id),
#         (keyword_id, news_id)]
def insert_keyword_has_news(data):
    sql = """INSERT INTO `keyword_has_news`(keyword_id, news_id) VALUES (%s, %s);"""
    execute_insert_many(sql, data)