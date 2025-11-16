# MongoDB 예제 프로젝트

이 프로젝트는 MongoDB와 MongoTemplate을 사용하여 데이터를 저장하고, Converter를 통해 서로 다른 DTO로 변환하는 예제입니다.

## 주요 기능

1. **외부 JSON 데이터 저장**: REST API를 통해 JSON 데이터를 받아 MongoDB의 `demo` 컬렉션에 저장
2. **MongoTemplate 활용**: Spring Data MongoDB의 MongoTemplate을 사용한 데이터 저장 및 조회
3. **Converter를 통한 다양한 응답**: 동일한 Document를 두 가지 다른 DTO로 변환하여 응답

## API 엔드포인트

### 1. 사용자 생성 (POST)
```http
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "name": "홍길동",
  "email": "hong@example.com",
  "age": 30,
  "address": "서울시 강남구"
}
```

### 2. 기본 정보 조회 (GET)
```http
GET http://localhost:8080/api/users/basic
```

**응답 예시** (UserBasicResponse - id, name, email만 포함):
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "name": "홍길동",
    "email": "hong@example.com"
  }
]
```

### 3. 상세 정보 조회 (GET)
```http
GET http://localhost:8080/api/users/detail
```

**응답 예시** (UserDetailResponse - 모든 정보 + displayInfo):
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "name": "홍길동",
    "email": "hong@example.com",
    "age": 30,
    "address": "서울시 강남구",
    "createdAt": "2025-11-16T21:30:00",
    "displayInfo": "홍길동 (30세) - 서울시 강남구"
  }
]
```

## 테스트용 샘플 JSON 데이터

```json
{
  "name": "김철수",
  "email": "kim@example.com",
  "age": 25,
  "address": "부산시 해운대구"
}
```

```json
{
  "name": "이영희",
  "email": "lee@example.com",
  "age": 28,
  "address": "대구시 중구"
}
```

```json
{
  "name": "박민수",
  "email": "park@example.com",
  "age": 35,
  "address": "인천시 남동구"
}
```

## 주요 컴포넌트

### 1. UserDocument
- MongoDB의 `demo` 컬렉션에 저장되는 Document 클래스
- `@Document(collection = "demo")` 어노테이션 사용

### 2. Converter
- **UserDocumentToBasicResponseConverter**: Document → UserBasicResponse 변환
  - id, name, email만 포함
- **UserDocumentToDetailResponseConverter**: Document → UserDetailResponse 변환
  - 모든 필드 + displayInfo (조합된 정보) 포함

### 3. UserService
- MongoTemplate을 사용하여 데이터 저장 및 조회
- Converter를 활용하여 서로 다른 형태의 응답 생성

## MongoDB 실행

```bash
# Docker로 MongoDB 실행
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

## 실행 방법

1. MongoDB 실행 (위 Docker 명령어 또는 로컬 설치)
2. 애플리케이션 실행
3. Postman 또는 curl로 API 테스트

## 기술 스택

- Spring Boot 3.5.7
- Spring Data MongoDB
- Spring Data JPA (H2)
- Lombok
- Java 17
