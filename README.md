# TrendIT (트렌딧)

## 서비스 소개

### 서비스 개요
- IT 뉴스 트렌드 분석 및 시각화 서비스

### 소개

- 업로드 되는 IT 뉴스의 트렌드를 분석하고, 유저에게 시각화 제공
- 여러 편리한 기능(검색, 기업별 분석, 커뮤니티, 뉴스레터 등의 추가 기능 예정)을 제공

### 타겟층

- IT에 관심은 있지만 시간이 부족하고, 빠르게 트렌드의 변화를 확인하고 싶은 사람들

### 기대효과

- 짧은 시간에 원하는 정보를 한 눈에 확인 가능
- 취준생
    - IT 직무 결정
    - 관심 분야 찾기
    - 면접 또는 자소서 대비용 자료 찾기
    - 트렌드 팔로업
- 투자자
    - IT 시장의 기술 트렌드 및 이슈 확인
    - 최근 화제가 되는 테마 확인
- 일반인
    - IT 관련 트렌드 팔로업
    - 최근 IT 시장의 동향 확인

### 주요 기능

- 기사의 헤드라인 기반 빈출 키워드 분석 및 시각화
	- 시간대 별, 기간 별 인기 키워드 시각화
	- IT 기업별 뉴스 리스트와 키워드 분석
	- 키워드 별 뉴스 리스트 제공

## 기술 스택

- FE
    - HTML5, CSS3, Javascript, Node.js, Vue3, Vuex, Quasar
- BE
    - Java, SpringBoot, Swagger, JPA, QueryDSL, Hadoop, Python, FastAPI, KoNLPy, KOMORAN
- Infra
    - MySQL, Ubuntu, AWS EC2, Nginx, Docker, Jenkins

## 역할 분담

💡 전체 풀스택.프론트가 부족한 관계로 모두 풀스택 기반, 각자 중점을 두는 파트 존재

- 우시은 - 팀장, BE(Spring Boot, FastAPI), 데이터(Hadoop), 배포
- 권덕주 - BE(Spring Boot, FastAPI)
- 김하연 - FE 디자인, 데이터(Selenium), 배포
- 김혜준 - FE, BE(Spring Boot), 발표
- 유경훈 - FE, BE(Spring Boot), 발표
- 정승욱 - FE, BE(Spring Boot), UCC

PPT 및 QA등의 산출물 공통

## 서비스 특이점


![SystemArchitecture](./output/TrendIT%20System%20Architecture.png)

![DataPipeline](./output/TrendIT%20Data%20Pipeline.png)

- 2015년부터의 IT 뉴스 데이터 220만건 이상의 전체 키워드 분석
- 데이터 분석 파이프라인 설계 및 특정 시각에 자동 분석
- 사용자 사전 추가를 통한 정확도 향상

## 서비스 특장점

- 2015년부터의 IT 뉴스 데이터 220만건 이상 사용
- 사용자 사전을 추가해 키워드 판별의 정확도를 높이고, 유저가 원하는 키워드 검출 가능
- 매일 새벽 4시에 전 날의 뉴스를 크롤링하고 구축한 데이터파이프라인을 이용해 하둡 처리까지 완료하여 자동으로 분석
- 크롤링 및 데이터 처리, Hadoop, 웹 BE API를 처리하는 서버를 각각 분리하고, 좀 더 적절한 기술 스택을 사용
- 또한, 프론트엔드, MySQL, Jenkins 등도 모두 별도의 컨테이너로 처리하였으며, master 브랜치에 hook 걸어 자동 배포
- 일, 주, 월, 년 별 통계 데이터를 따로 저장하여 관련 부분 속도 및 성능 향상하였으며 그래프로 시각화
- Index 및 별도 캐싱 테이블 생성하여 200만여개 1억개 정도의 row를 가진 여러 테이블과 관련된 쿼리가 2분 30초 이상 걸렸으나 0.015초로 단축
  - (별도 캐싱 테이블 생성에 매일 60여초 소요되며 이는 스케줄링된 작업에 포함)
- 이외 다른 쿼리도 2초에서 0.016초 정도로 전반적 속도 개선

# Jira와 Git 컨벤션

## Jira

### Sprint

- 1주 단위로 반복

### Issue의 종류

- Epic
    - 최상위 수준의 기능, 작업의 단위
    - 여러 Sprint에 걸쳐서 진행되는 정도의 범위
    - ex) 회원 관리, 로그인 관리
- Story
    - Epic의 하위 단계 기능, 작업의 단위
    - ex) 회원가입, 회원 정보 수정, 회원 탈퇴, 로그인, 비밀번호 찾기
- Task
    - 개발에 해당되지는 않는 작업이나, Sprint 안에 포함하여 해야 하는 일
    - ex) ERD, 테스트 케이스 작성
- Bug (option)
    - 프로젝트 개발, 검증 중 발견된 버그
    - ex) 상품 검색시 특정 상품이 조회되지 않는 문제
- Subtask
    - 위 issue의 종류에서 세부 단위 작업이 필요할 때 등록함
    - ex) OPEN API를 호출하여 최근글을 JSON으로 호출하여 출력한다.

### Issue의 이름 정하기

```bash
[type] 이슈 이름
```

- type : FE / BE / ETC

### Issue Cycle

1. 스프린트 생성
2. 백로그에 issue 추가
3. 적절한 단위의 Epic 등록
4. 조금 더 작은 단위의 Story를 등록하고 Epic에 포함 시키기
    1. 타입 지정
    2. 제목 지정
    3. 중요도 지정 (option)
    4. Label은 기능(feature), 버그(bug), 문서(docs) 등에서 선택하여 작성 (option)
    5. Assignee 지정
    6. Story Point 지정
        1. 4점 이하로 지정하는 것을 권장
        2. 인당 1주에 40점 이상을 진행하는 것을 권장함
5. Story를 조금 더 작은 단위로 분리하기 위해서는 **Subtask 활용**하여 등록
6. 추가된 issue를 백로그에서 꺼내어 Sprint에 등록하고 Sprint 활성화
7. 진행 과정에 맞추어 issue의 상태를 변경

## Git Branch 전략

### 브랜치 소개

- **master(main) 브랜치**
    - **제품으로 출시될 수 있는 브랜치**
        - 배포 이력을 관리하기 위해 사용함
        - 배포가능한 상태만을 관리
- **develop 브랜치**
    - **다음 출시 버전을 개발하는 브랜치**
        - 기능 개발을 위한 브랜치들을 병합하기 위해 사용
    - 개발 중인 코드의 중심이 되는 브랜치
        - 코드를 수정하고 싶을 때는 develop 브랜치를 중심으로 새로운 feature 브랜치를 생성
        - 만들어진 feature 브랜치를 기반으로 기능 추가와 코드 수정이 이루어짐.
        - feature 브랜치에서 개발이 끝나면 develop 브랜치에 merge를 수행한다.
- **feature 브랜치**
    - **기능을 개발하는 브랜치**
        - 새로운 기능 개발 또는 버그 수정이 필요할 때마다 develop 브랜치로부터 분기
        - 브랜치의 이름은 [issue number]/[기능-요약]의 형식으로 작성
        - ex) `S07P12A302-1/login-user`
- **hotfix 브랜치**
    - **출시 버전에서 발생한 버그를 수정하는 브랜치**
    - hotfix 브랜치는 릴리즈된 프로그램에서 발생한 버그들을 수정하기 위한 브랜치
    - master 브랜치로부터 파생되며 수정한 버그들을 적용하기 위해 master와 develop 브랜치 모두에 병합

### 사용

- master, develop, feature, hotfix 브랜치 사용

### Pull Request 보내기

- feature branch를 develop에 merge하고자 할 경우 PR을 보내고, 코드 리뷰 또는 의견 공유 진행
- 진행 상황을 확인하는 것에도 도움이 되므로 PR 생기면 꼭 확인하기
- 작성은 템플릿을 기반으로 변형하며 작성

## Git Commit Message

### Commit message 구조

```bash
[issueNumber] type: Subject

body

footer
```

- 제목과 본문, 본문과 푸터 사이에는 반드시 한 줄 띄우기

### Commit Type

```bash
- feat 		 : 새로운 기능 추가
- fix 	   : 버그 수정
- design   : CSS등의 사용자 UI 변경
- docs  	 : 문서 수정
- style  	 : 코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우
- refactor : 코드 리팩토링
- comment  : 주석 추가 및 변경
- test 		 : 테스트 코드, 리팩토링 테스트 코드 추가 (프로덕션 코드 변경 X)
- chore 	 : 빌드 업무 수정, 패키지 매니저 수정 (프로덕션 코드 변경 X)
- rename   : 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
- remove   : 파일을 삭제하는 작업만 수행한 경우
```
