import pymysql
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.ui import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager

def crawl_data(driver, wait, begin_date, end_date):
    # 기간 설정
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

    # 통합 분류 설정
    classification = driver.find_element(by=By.XPATH, value='//*[@id="collapse-step-1-body"]/div[3]/div/div[2]/div[1]/a')
    classification.click()
    it_science = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/div/span[3]/label')
    it_science.click()

    expander = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/div/span[2]')
    expander.click()
    science = driver.find_element(by=By.XPATH, value='//*[@id="srch-tab3"]/ul/li[8]/ul/li[2]/div/span[4]')
    science.click()

    # 적용하기 클릭
    apply = driver.find_element(by=By.XPATH, value='//*[@id="search-foot-div"]/div[2]/button[2]')
    apply.click()

    # 총 페이지 조회 및 시작 페이지 설정
    wait.until(EC.presence_of_element_located((By.XPATH, '//*[@id="news-results-tab"]')))
    last_page = int(driver.find_element(by=By.XPATH, value='//*[@id="news-results-tab"]/div[1]/div[2]/div/div/div/div/div[3]/div/b').text)
    paging = driver.find_element(by=By.XPATH, value='//*[@id="paging_news_result"]')
    cur_page = 1
    paging.send_keys(Keys.BACKSPACE)
    paging.send_keys(cur_page)
    paging.send_keys(Keys.ENTER)

    # 크롤링 실행
    data = []
    while cur_page <= last_page :
        wait.until(EC.invisibility_of_element_located((By.XPATH, '//*[@id="collapse-step-2-body"]/div/div[2]/div[1]')))
        news_items = driver.find_elements(by=By.CLASS_NAME, value='news-item')
        for item in news_items:
            headline = item.find_element(by=By.XPATH, value='div/div[2]/a/div/strong/span').text
            img_link = item.find_element(by=By.XPATH, value='div/div[1]/a').get_attribute("style")[23:-3]
            news_content = item.find_element(by=By.XPATH, value='div/div[2]/a/p').text
            info = item.find_element(by=By.XPATH, value='div/div[2]/div')
            link_exists = info.find_elements(by=By.CLASS_NAME, value='provider')
            news_agency = info.find_element(by=By.XPATH, value='div/a').text if len(link_exists) > 0 else driver.execute_script("return arguments[0].firstChild.textContent", info.find_element(by=By.XPATH, value='div')).strip()
            news_date = info.find_element(by=By.XPATH, value='p[1]').text
            news_link = info.find_element(by=By.XPATH, value='div/a').get_attribute("href") if len(link_exists) > 0 else ""
            data.append(tuple([headline, img_link, news_agency, news_content, news_date, news_date]))
            
        next_page = driver.find_element(by=By.XPATH, value='//*[@id="news-results-tab"]/div[1]/div[2]/div/div/div/div/div[4]/a')
        next_page.send_keys(Keys.ENTER)
        cur_page += 1

    return data


def insert_database(conn, sql, data) :
    cursor = conn.cursor()
    cursor.executemany(sql, data)
    conn.commit()
    cursor.close()
    conn.close()


##### 메인

# DB 커넥션 생성
conn = pymysql.connect(
    user="trendit",
    passwd="",
    host="localhost",
    db="trendit"
)

URL = 'https://www.bigkinds.or.kr/v2/news/index.do'

# 크롬 웹드라이버 생성
options = Options()
options.add_argument('--headless')
options.add_argument('--window-size=1920,1080')
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
driver.implicitly_wait(20)
driver.get(URL)
wait = WebDriverWait(driver, 20)

# 크롤링 실행
data = crawl_data(driver, wait, "2018-01-01", "2018-01-01")
driver.quit()

# DB 저장
sql = """INSERT INTO `news`(headline, img_link, news_agency, news_content, news_date, news_link) VALUES (%s, %s, %s, %s, %s, %s);"""
insert_database(conn, sql, data)
