# ethereum-indexer
- 이 프로젝트는 Ethereum 메인넷에서 ETH와 ERC20 토큰의 보유량을 추적하는 것을 목표로 하고 있습니다.

## 실행 방법

### 실행 요구 사항
- 해당 애플리케이션은 jdk 17버전 이상을 요구합니다.

### 실행 전 세팅
1. root 폴더에 config 폴더 생성 후 infura.yml 파일을 만들고, infura에서 발급받은 ApiKey를 추가합니다.
   ![img_5](https://github.com/user-attachments/assets/f8f167ba-ad6c-4e79-9278-d9d72e67c71d)

- 예시
  ``` 
  infura:
    apiKey: a55c3433b235406663e35802030de0ae
  ```

2. /src/main/resources 하위 application.yml 파일에서 database 및 redis 관련 정보를 수정합니다.
- DB 정보 예시

  ![img_3](https://github.com/user-attachments/assets/01737252-06ea-47ad-9dfb-b360a4150c70)
  - localhost:5432 -> DB host 및 port 번호를 변경해 주세요
  - postgres -> 사용하시는 데이터베이스 명으로 변경해주세요

- Redis 정보 예시

  ![img_4](https://github.com/user-attachments/assets/73bfcc93-1c5c-4ecd-93e9-381c6061061a)
  - host: localhost -> 사용하시는 redis의 host로 변경해주세요
  - port: 6379 -> 사용하시는 redis의 port로 변경해주세요
  - password -> 사용하시는 redis의 password로 변경해주세요

3. API 요청 전 최근 7일 생성된 블록에 대한 데이터 적재가 필요합니다.
  - 적재는 다음과 같은 과정을 거칩니다.
  - Transactional
    - block 데이터 생성
    - transaction 데이터 생성
    - log 데이터 생성
    - ERC-20 transfer 데이터 생성
  - 위 데이터 모두 생성 후 일별 블록 번호 데이터 생성 (해당 과정이 끝나야 API를 통한 보유량 조회가 가능합니다.)

4. (Optional) 추천 사항
- infura 무료 플랜의 경우 초당 rate limit이 500 크레딧이므로 애플리케이션이 정상 작동하지 않을 수 있습니다.
  - https://developer.metamask.io/ > Configure > Settings > Key Credit Limits > Per second credit rate-limiting을 10000 정도로 세팅하시는 걸 추천드립니다.



### 실행 명령어
``` ./gradlew bootrun ```

## 패키지 설명
- client
  - 외부 시스템(API)과 통신하는 로직을 구현하는 클래스들을 포함합니다.
  - 현재는 Infura를 통해 ethereum mainnet에 RPC요청을 보내는 로직이 구현되어 있습니다.
- config
  - 애플리케이션의 설정 클래스들로 구성됩니다.
- controller
  - REST API 엔드포인트를 정의하고, 클라이언트 요청을 처리하는 클래스들을 포함합니다.
  - 현재는 요청한 계정에 대한 토큰 보유량 반환 API가 존재합니다.
- entity
  - 데이터베이스와 매핑되는 JPA 엔터티 클래스들을 포함합니다.
- exception
  - 애플리케이션에서 사용하는 커스텀 예외 클래스들을 포함합니다.
- facade
  - 여러 서비스와 클라이언트를 조합하여 비즈니스 로직을 단순화하고 캡슐화한 클래스를 포함합니다.
- model
  - request
    - Client측 요청 모델을 관리합니다.
  - dto
    - 애플리케이션 내부에서 데이터를 전달하거나 표현하기 위한 클래스를 포함합니다.
  - response
    - Client로 반환되는 응답 모델을 관리합니다.
  - result
    - 외부 API 응답 모델을 관리합니다.
- repository
  - 데이터베이스와의 CRUD 작업을 처리하는 클래스들을 포함합니다.
- scheduler
  - 특정 시간 간격으로 작업을 실행하거나 예약된 작업을 처리하는 클래스들을 포함합니다.
  - 현재는 블록, 트랜잭션, 로그 관련 데이터 적재 스케줄러가 존재합니다.
- service
  - 비즈니스 로직을 처리하고 데이터 흐름을 제어하는 클래스들을 포함합니다.
- spec
  - 클라이언트를 추상화하기 위한 인터페이스를 포함합니다.
- util
  - 공통적으로 사용되는 유틸리티 클래스들을 포함합니다.


## API 명세
해당 애플리케이션 실행 후, http://localhost:8080/docs 로 접속하시면 됩니다.
![image](https://github.com/user-attachments/assets/c44fd303-4e04-4389-a506-d65107191d7a)

### API 테스트
데이터 적재 후 /http/accountToken.http 파일을 통해 테스트 가능합니다.