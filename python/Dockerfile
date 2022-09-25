FROM ubuntu:latest

WORKDIR /code

ENV LANG=C.UTF-8
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends tzdata g++ curl

# install java
RUN apt-get install -y openjdk-8-jdk
ENV JAVA_HOME="/usr/lib/jvm/java-1.8-openjdk"

# install python
RUN apt-get install -y python3-pip python3-dev
RUN cd /usr/local/bin && \
  ln -s /usr/bin/python3 python && ln -s /usr/bin/pip3 pip && \
  pip install --upgrade pip

# apt clean
RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# ssh, wget, xvfb, unzip
RUN apt-get update
RUN apt-get install openssh-client wget xvfb unzip -y

# Chrome, Chrome Driver
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update
RUN apt-get install google-chrome-stable -y
RUN wget -N http://chromedriver.storage.googleapis.com/105.0.5195.52/chromedriver_linux64.zip -P /code
RUN unzip /code/chromedriver_linux64.zip

# copy resources
COPY . /code

# Window Line
RUN sed -i 's/\r$//' /code/script.sh

# install python package
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

EXPOSE 9090

CMD ["uvicorn", "app.main:app", "--proxy-headers", "--host", "0.0.0.0", "--port", "9090"]