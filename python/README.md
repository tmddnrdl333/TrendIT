## pymysql 설치

```bash
pip install pymysql
```

## KoNLPy 설치

### 우분투

```bash
$ sudo apt-get install g++ openjdk-7-jdk # Install Java 1.7+
$ sudo apt-get install python-dev; pip install konlpy     # Python 2.x
$ sudo apt-get install python3-dev; pip3 install konlpy   # Python 3.x
```

### 윈도우

1. 설치 후, JAVA_HOME 설정하기 (Java 1.7 버전 이상 필요)

2. JPype1 (>=0.5.7)을 다운로드 받고 설치
    - 다운 받은 .whl 파일을 설치하기 위해서는 pip 을 업그레이드 해야할 수 있음
    ```bash
    pip install --upgrade pip
    pip install JPype1-0.5.7-cp27-none-win_amd64.whl
    ```

3. 명령 프롬프트로 KoNLPy 설치하기
    ```bash
    pip install konlpy
    ```