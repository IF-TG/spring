
# TravelPlan

사용자에게 여행지 정보와 커뮤니케이션 환경을 제공하며 최종적으로 여행을 계획할 수 있도록 하는 IOS기반의 애플리케이션입니다. 따라서 Spring 프레임워크를 사용하여 기본적인 CRUD를 제공하며, 사용자가 쾌적하게 사용할 수 있도록 관광지 검색 기능과 관광지 추천 기능을 구현했습니다. 보안에서는 Spring security의 OAuth2Login을 기반으로 사용자가 편리하게 로그인할 수 있도록 제공하였습니다.
 - https://github.com/IF-TG
 - [docker-compose 팀원용](https://github.com/IF-TG/spring/wiki/docker%E2%80%90compose-(%ED%8C%80%EC%9B%90%EC%9A%A9))
## API 명세서
 - [https://travelplan.s3.ap-northeast-2.amazonaws.com/index.html](https://travelplan.s3.ap-northeast-2.amazonaws.com/index.html)
   
<details>
  <summary>상태 코드</summary>
 
| HttpStatus           | 에러 코드 | 메시지                                              | enum                 |
|----------------------|------------|---------------------------------------------------|-----------------------|
| OK                   | 0000       | SUCCESS                                           | SUCCESS               |
| BAD_REQUEST          | 2001       | 알 수 없는 컨텐츠 타입                             | INVALID_CONTENT_TYPE_ID |
| NOT_FOUND            | 2011       | 삭제되었거나 알 수 없는 댓글입니다.                    | NOT_FOUND_COMMENT     |
| NOT_FOUND            | 2012       | 삭제되었거나 알 수 없는 글입니다.                     | NOT_FOUND_POST        |
| NOT_FOUND            | 2013       | 이미 삭제된 댓글입니다.                              | IS_ALREADY_DELETE     |
| NOT_FOUND            | 2014       | 알 수 없는 컨텐츠입니다.                             | NOT_FOUND_CONTENT     |
| NOT_FOUND            | 4000       | 알 수 없는 사용자                                   | NOT_FOUND_USER        |
| UNAUTHORIZED         | 4001       | 로그인이 만료되었습니다.                             | EXPIRED_LOGIN         |
| UNAUTHORIZED         | 4002       | 로그인 유지 시간 만료되었습니다.                      | EXPIRED_TOKEN         |
| UNAUTHORIZED         | 4003       | 유효하지 않는 접근입니다.                             | NOT_VALID_TOKEN       |
| CONFLICT             | 4005       | 중복된 닉네임입니다.                                  | DUPLICATE_NICKNAME    |
| FORBIDDEN            | 4444       | 잘못된 접근입니다.                                   | AUTHENTICATION_FAILED |
| FORBIDDEN            | 4445       | 권한이 없습니다.                                    | AUTHORITY_FAILED      |
| BAD_REQUEST          | 4008       | 유효하지 않은 날짜 형식입니다.                        | INVALID_DATE_FORMAT   |
| BAD_REQUEST          | 4006       | 지원하지 않는 포멧입니다.                             | INVALID_IMAGE_TYPE    |
| INTERNAL_SERVER_ERROR| 5001       | 죄송합니다. 요청을 처리하는 과정에서 예상치 못한 서버 오류가 발생했습니다. 문제를 빠르게 해결하기 위해 노력하고 있습니다. 문제가 지속될 경우, 이메일로 문의해 주세요. | Internal_Server_Error |
| NOT_IMPLEMENTED      | 5002       | 로그인 시도 중 문제가 발생하였습니다.                  | UNSUPPORTED_OAUTH2_REQUIREMENT |
| INTERNAL_SERVER_ERROR| 5003       | 이미지 저장에 실패했습니다.                           | CANNOT_CREATE_FOLDER |            

</details>

### ERD
 - https://github.com/IF-TG/spring/blob/master/ERD.png
## 사용된 기술
- 백엔드: Spring, Spring Boot, Spring Security, JPA, Query dsl
- 데이터베이스: MySQL
- 검색 엔진: Elasticsearch
- 인증 관리 & 일부 기능 캐시 : Redis
## 프로젝트 협업 과정
 - [Notion (노션 메인 사이트 링크)](https://www.notion.so/d00c55ded166441bb7991ff3a28b4d73)
## 주요 기능 구현
 - [관광지 검색 기능 (word2vec, text rank, chat gpt, elasticsearch)](https://github.com/IF-TG/spring/wiki/GET-:-%EA%B2%80%EC%83%89-%EA%B8%B0%EB%8A%A5-(with-Elasticsearch,-Chat-GPT,-Word2Vec,-Text-Rank))
 - [관광지 추천 기능 (doc2vec)](https://github.com/IF-TG/spring/wiki/GET:-%EA%B4%80%EA%B4%91%EC%A7%80-%EC%B6%94%EC%B2%9C-%EA%B8%B0%EB%8A%A5-(-doc2vec-))
 - [OAuth2 Login&JWT 발급](https://github.com/MoonDooo/authentication_server)
 - [게시글 Querydsl 동적 쿼리](https://github.com/IF-TG/spring/wiki/GET-:--Posts,-Query-dsl-%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC)

#### 그 외 기능
 - 스프링 jpa 데이터를 활용한 댓글, 후기, 계획 기본적인 crud
 - @RestControllerAdvice, @ExceptionHandler를 사용한 예외 처리
 - 후기, 프로필 이미지 저장, 삭제
 - 유저 차단 기능
 - 좋아요, 스크랩 토글 기능
 - AOP로 함수 시작과 끝 log 기록 (@Time)
