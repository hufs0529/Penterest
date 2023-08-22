<br />
<div align="center">
  <a href="https://zenml.io">
    <img alt="MoviePy" src="https://github.com/hufs0529/penterest/assets/81501114/da23f839-7e6a-4103-816c-dc34d4b9d0b2" alt="Logo" width="150" height="100">
    <img alt="flask" src="https://github.com/hufs0529/penterest/assets/81501114/12d4d126-20bb-4503-8450-d085750c8ae0" alt="Logo" width="150" height="100">
    <img alt="s3" src="https://github.com/hufs0529/penterest/assets/81501114/9bdbe708-6664-4a31-897f-921fd73e542f" alt="Logo" width="150" height="100">
  </a>

<h3 align="center">Video to Gif Converter & Save in S3</h3>

  <p align="center">
    Video -> Gif 변환 및 S3 적재 API
    <br />
  </p>
</div>



<br />

# 🤖 Introduction

🤹 Penterest 서비스의 Gif 검색 기능을 위해서 동영상 업로드시 Gif변환 및 OpenAI-CLIP 모델을 사용한 Caption 생성
-  flask 서버를 통한 동영상 업로드
-  OpenAI-CLIP을 통한 Caption 생성 -> S3에 Gif적재
-  Spring 서버 적재를 위한 S3 Url과 Caption 반환

<div align="center">
    <img src="docs/book/.gitbook/assets/stack.gif">
</div>

# 🔋 Architecture
<img width="590" alt="flask아키" src="https://github.com/hufs0529/penterest/assets/81501114/4c7ab6fb-3d91-4e29-83a3-4be322c59ea2">




# 🤸 Quickstart

Java 17, MySQL is required:

#### 1. Docker로 시작하기
```bash
docker build -t penterest .
docker run penterest
```

# 🖼️ About Main Services

### 1. JWT Token 기반 로그인
### 2. 동영상 업로드 및 Gif 전환
### 3.검색엔진
#### 3-1. 전환된 Gif의 Caption 기반 ElasticSearch 검색엔진
#### 3-2. 전환된 Gif의 Comment 기반 ElasticSearch 검색엔진
#### 3-3. TypedQuery를 이용한 Gif 조회
### 4. 게시글 좋아요
#### 4-1. 좋아요한 게시글 조
### 5. Member 팔로우
#### 5-1. 팔로우, 팔로잉 관계
#### 5-2. 팔로우, 팔로잉 수

