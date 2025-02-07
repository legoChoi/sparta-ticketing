## 공연 티켓팅 프로그램 by 문제적남자들

---
### _Goal_

> 티켓팅 프로그램을 통한 동시성 문제의 이해와 해결
---
### _Team_
`팀장: 한성우 - 동시성 제어, 서비스(기능) 확장, Auth`
<br>
`팀원: 김형준 - 동시성 제어, 캐싱`
<br>
`팀원: 김효중 - 동시성 제어, 인덱싱`
<br>
`팀원: 최우탁 - 동시성 제어, CI/CD`

---
### _Skills_
[![Team_Skills](https://skillicons.dev/icons?i=github,spring,mysql,redis,aws,docker)](https://skillicons.dev)

---
### _Plan_
- [x] 티켓팅 기본 CRUD
- [x] 동시성 이슈 발생하는 부분 파악 후 회의
- [x] 동시성 이슈 검증을 위한 테스트 코드 작성
- [x] Redis Lock 통한 동시성 이슈 제어
- [x] AOP 방식 Lock 구현 (선택)
- [x] CI/CD 구축(GitHub Actions)
- [x] 서비스 확장
- [x] 성능 개선

---
### _Wire Frame_
![Architecture](https://github.com/legoChoi/sparta-ticketing/blob/master/architecture.png?raw=true)

---
### _Wire Frame_
![Ticketing_ERD](https://github.com/legoChoi/sparta-ticketing/blob/master/wire_frame.png?raw=true)

---

### _ERD_
![Ticketing_ERD](https://github.com/legoChoi/sparta-ticketing/blob/master/erd.png?raw=true)

---

### _Search Performance Test_
#### Local Memory Cache
검색어 전체 검색 결과를 캐싱하는 방식

- 무작위 검색

![Local_Random](https://github.com/legoChoi/sparta-ticketing/blob/master/local_memory_random_search.png?raw=true)

- 인기 검색어

![Local_Best](https://github.com/legoChoi/sparta-ticketing/blob/master/local_memory_best.png?raw=true)

- 인기 검색어 단어의 일부

![Local_Partial](https://github.com/legoChoi/sparta-ticketing/blob/master/local_memory_best_partial.png?raw=true)

#### Local Memory & Paging Cache
페이징 검색에 대한 결과를 캐싱하는 방식

- 무작위 검색

![Paging_Random](https://github.com/legoChoi/sparta-ticketing/blob/master/paging_cache_random_search.png?raw=true)

- 인기 검색어

![Paging_Best](https://github.com/legoChoi/sparta-ticketing/blob/master/paging_cache_best.png?raw=true)

- 인기 검색어 단어의 일부

![Paging_Partial](https://github.com/legoChoi/sparta-ticketing/blob/master/paging_cache_best_partial.png?raw=true)


#### Redis Cache
검색 결과를 Redis에 캐싱하는 방식

- 무작위 검색

![Redis_Random](https://github.com/legoChoi/sparta-ticketing/blob/master/redis_random_search.png?raw=true)

- 인기 검색어

![Redis_Best](https://github.com/legoChoi/sparta-ticketing/blob/master/redis_best.png?raw=true)

- 인기 검색어 단어의 일부

![Redis_Partial](https://github.com/legoChoi/sparta-ticketing/blob/master/redis_partial_best.png?raw=true)
