
# TravelPlan

사용자에게 여행지 정보와 커뮤니케이션 환경을 제공하며 최종적으로 여행을 계획할 수 있도록 하는 IOS기반의 애플리케이션입니다. 따라서 Spring 프레임워크를 사용하여 기본적인 CRUD를 제공하며, 사용자가 쾌적하게 사용할 수 있도록 관광지 검색 기능과 관광지 추천 기능을 구현했습니다. 보안에서는 Spring security의 OAuth2Login을 기반으로 사용자가 편리하게 로그인할 수 있도록 제공하였습니다.

## API 명세서
 - [https://travelplan.s3.ap-northeast-2.amazonaws.com/index.html](https://travelplan.s3.ap-northeast-2.amazonaws.com/index.html)

## 사용된 기술
- 백엔드: Spring, Spring Boot, Spring Security, JPA, Query dsl
- 데이터베이스: MySQL
- 검색 엔진: Elasticsearch
- 인증 관리 & 일부 기능 캐시 : Redis
## 프로젝트 협업 과정
 - [Notion (노션 메인 사이트 링크)](https://www.notion.so/d00c55ded166441bb7991ff3a28b4d73)
## 주요 기능 구현
 - [관광지 검색 기능 (word2vec, text rank, chat gpt, elasticsearch)](https://github.com/IF-TG/spring/wiki/GET-:-%EA%B2%80%EC%83%89-%EA%B8%B0%EB%8A%A5-(with-Elasticsearch,-Chat-GPT,-Word2Vec,-Text-Rank))
 - [관광지 추천 기능 (doc2vec)](https://github.com/IF-TG/spring/wiki/%EA%B4%80%EA%B4%91%EC%A7%80-%EC%B6%94%EC%B2%9C-%EA%B8%B0%EB%8A%A5-(-doc2vec-))
 - [OAuth2 Login]
 - [게시글 Querydsl 동적 쿼리](https://github.com/IF-TG/spring/wiki/GET-:--Posts,-Query-dsl-%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC)
 - [이미지 저장]
