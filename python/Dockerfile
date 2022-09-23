#
FROM python:3.9

#
WORKDIR /code

#
COPY ./requirements.txt /code/requirements.txt

RUN pip install --upgrade pip

RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

RUN apt-get update

RUN apt-get install g++ openjdk-8-jdk; pip3 install konlpy

RUN pip install selenium webdriver-manager pymysql

COPY ./J7A301T.pem /code/J7A301T.pem

COPY ./script.sh /code/script.sh

COPY ./app /code/app

#
CMD ["uvicorn", "app.main:app", "--proxy-headers", "--host", "0.0.0.0", "--port", "9090"]

