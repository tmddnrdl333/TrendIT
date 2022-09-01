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
