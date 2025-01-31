## 공연 티켓팅 프로그램 by 문제적남자들

---
### _Goal_

> 티켓팅 프로그램을 통한 동시성 문제의 이해와 해결
---
### _Team_
`팀장: 한성우`
<br>
`팀원: 김형준`
<br>
`팀원: 김효중`
<br>
`팀원: 최우탁`

---
### _Skills_
[![Team_Skills](https://skillicons.dev/icons?i=java,spring,mysql)](https://skillicons.dev)

---
### _Plan_
- [ ] 티켓팅 기본 CRUD
- [ ] 동시성 이슈 발생하는 부분 파악 후 회의
- [ ] 동시성 이슈 검증을 위한 테스트 코드 작성
- [ ] Redis Lock 통한 동시성 이슈 제어
- [ ] AOP 방식 Lock 구현 (선택)
- [ ] Redis 대신 MySQL 통한 Lock 구현

---
### _Wire Frame_
![Ticketing_ERD](https://file.notion.so/f/f/83c75a39-3aba-4ba4-a792-7aefe4b07895/f300edbb-5d12-4696-9a32-1f7d1949f26a/image.png?table=block&id=516b7c97-20d8-4f50-847e-2a4010150484&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1738339200000&signature=UF8tWlB9Ne3JfHCkrihp92cVd6UwrRQIssCPYXJQDrk&downloadName=image.png)

---

### _ERD_
![Ticketing_ERD](https://file.notion.so/f/f/83c75a39-3aba-4ba4-a792-7aefe4b07895/e9801175-b3ed-47a2-ae7f-973c7858eb63/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2025-01-31_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_5.38.58.png?table=block&id=1166535f-9d5b-4064-8975-e902421011e8&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1738339200000&signature=rJjWyQ1W91gWjAFrw7HXhR0w24eizoTV8ZlSaWGCv00&downloadName=%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2025-01-31+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+5.38.58.png)
