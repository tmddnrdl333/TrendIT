import os
import time
from datetime import datetime
from datetime import timedelta
import json
import pymysql
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager

## DB 세팅 
conn = pymysql.connect(
    user="trendit",
    passwd="trendit829",
    host="localhost",
    db="trendit"
)
cursor = conn.cursor(pymysql.cursors.DictCursor)

URL = 'https://www.bigkinds.or.kr/v2/news/index.do'

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.implicitly_wait(20)
driver.get(URL)

begin_date = '2022-07-31'
end_date = '2022-08-06'
date_format = '%Y-%m-%d'

for i in range(10):
    time.sleep(3)
    ## 기간
    period = driver.find_element(by=By.XPATH, value='//*[@id="collapse-step-1-body"]/div[3]/div/div[1]/div[1]/a')
    period.click()
    begin = driver.find_element(by=By.XPATH, value='//*[@id="search-begin-date"]')
    begin.send_keys(Keys.BACKSPACE * 10)
    begin.send_keys(begin_date)
    begin.send_keys(Keys.ENTER)
    end = driver.find_element(by=By.XPATH, value='//*[@id="search-end-date"]')
    end.send_keys(Keys.BACKSPACE * 10)
    end.send_keys(end_date)
    end.send_keys(Keys.ENTER)

    if i == 0 :
        ## 통합 분류
        classification = driver.find_element(by=By.XPATH, value='//*[@id="collapse-step-1-body"]/div[3]/div/div[2]/div[1]/a')
        classification.click()
        time.sleep(0.5)
        it_science = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/div/span[4]')
        it_science.click()

        expander = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/div/span[2]')
        expander.click()
        science = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/ul/li[2]/div/span[4]')
        science.click()

    ## 적용하기
    apply = driver.find_element(by=By.XPATH, value='//*[@id="search-foot-div"]/div[2]/button[2]')
    apply.click()

    time.sleep(2)
    last_page = int(driver.find_element(by=By.XPATH, value='//*[@id="news-results-tab"]/div[1]/div[2]/div/div/div/div/div[3]/div/b').text)
    print(last_page)
    paging = driver.find_element(by=By.XPATH, value='//*[@id="paging_news_result"]')
    cur_page = 1
    paging.send_keys(Keys.BACKSPACE)
    paging.send_keys(cur_page)
    paging.send_keys(Keys.ENTER)
        
    sql = "INSERT INTO news(headline, img_link, news_agency, news_content, news_date, news_link) VALUES (%s, %s, %s, %s, %s, %s)"

    while cur_page <= last_page :
        time.sleep(2)
        news_items = driver.find_elements(by=By.CLASS_NAME, value='news-item')
        for item in news_items:
            headline = item.find_element(by=By.XPATH, value='div/div[2]/a/div/strong/span').text
            img_link = item.find_element(by=By.XPATH, value='div/div[1]/a').get_attribute("style")[23:-3]
            news_content = item.find_element(by=By.XPATH, value='div/div[2]/a/p').text

            info = item.find_element(by=By.XPATH, value='div/div[2]/div')
            link_exists = info.find_elements(by=By.CLASS_NAME, value='provider')
            news_agency = info.find_element(by=By.XPATH, value='div/a').text if len(link_exists) > 0 else driver.execute_script("return arguments[0].firstChild.textContent", info.find_element(by=By.XPATH, value='div')).strip()
            ## print(news_agency)
            news_date = info.find_element(by=By.XPATH, value='p[1]').text
            news_link = info.find_element(by=By.XPATH, value='div/a').get_attribute("href") if len(link_exists) > 0 else ""
            cursor.execute(sql, (headline, img_link, news_agency, news_content, news_date, news_link))

        next_page = driver.find_element(by=By.XPATH, value='//*[@id="news-results-tab"]/div[1]/div[2]/div/div/div/div/div[4]/a')
        next_page.send_keys(Keys.ENTER)
        cur_page += 1

    conn.commit()
    
    step1_button = driver.find_element(by=By.XPATH, value='//*[@id="collapse-step-1"]')
    step1_button.click()

    begin_date_obj = datetime.strptime(begin_date, date_format)
    begin_date = (begin_date_obj - timedelta(days = 7)).strftime(date_format)
    end_date_obj = datetime.strptime(end_date, date_format)
    end_date = (end_date_obj - timedelta(days = 7)).strftime(date_format)

conn.close()
