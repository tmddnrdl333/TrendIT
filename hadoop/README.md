## 하둡 실행

1. 빌드
    
    ```bash
    cd trendit
    ant
    ```
    
2. 테스트 데이터 HDFS에 넣기
    
    ```bash
    hdfs dfs -mkdir trendit_keyword
    hdfs dfs -put data/wordcount-data.txt trendit_keyword
    ```
    
    - hadoop의 HDFS에 `trendit_keyword` 디렉토리를 생성
    - Linux의 data 디렉토리에 있는 wordcount-data.txt 파일을 하둡의 HDFS의 `trendit_keyword` 디렉토리에 보냄
3. MapReduce 알고리즘 코드 실행
    
    ```bash
    hadoop jar trendit.jar wordcount trendit_keyword wordcount_keyword_out
    ```
    
    - Driver.java에 표시한 대로 wordcount를 써서 Wordcount 맵 리듀스 코드 수행
    - Wordcount_test 디렉토리에 들어있는 모든 파일을 맵 함수의 입력으로 사용
    
4. 결과 확인
    
    ```bash
    hdfs dfs -ls wordcount_keyword_out
    ```
    
    - 내부의 파일 cat해서 확인하면 됨

    ```bash
    hdfs dfs -cat wordcount_keyword_out/part-r-00000 | more >> output1.txt
    hdfs dfs -cat wordcount_keyword_out/part-r-00001 | more >> output2.txt
    ```

## Komoran Test exec (deprecated)
```
javac -cp "./libs/KOMORAN-3.3.4.jar: komoran-test.java"
javac -cp ".:./libs/KOMORAN-3.3.4.jar:" komoran-test

```