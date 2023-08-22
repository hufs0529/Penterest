<br />
<div align="center">
  <a href="https://zenml.io">
    <img alt="spring" src="https://github.com/hufs0529/penterest/assets/81501114/7d3522e9-9e2a-4bbf-b06b-6748f23a84c6" alt="Logo" width="150" height="100">
    <img alt="react" src="https://github.com/hufs0529/penterest/assets/81501114/5097de8f-df6a-46fa-8de4-85155d0955ff" alt="Logo" width="150" height="100">
    <img alt="elk" src="https://github.com/hufs0529/penterest/assets/81501114/54c93a7c-211c-4f4f-ac35-9cb993d2c38a" alt="Logo" width="200" height="100">
    <img alt="MoviePy" src="https://github.com/hufs0529/penterest/assets/81501114/da23f839-7e6a-4103-816c-dc34d4b9d0b2" alt="Logo" width="150" height="100">
    <img alt="flask" src="https://github.com/hufs0529/penterest/assets/81501114/12d4d126-20bb-4503-8450-d085750c8ae0" alt="Logo" width="150" height="100">
    <img alt="s3" src="https://github.com/hufs0529/penterest/assets/81501114/9bdbe708-6664-4a31-897f-921fd73e542f" alt="Logo" width="150" height="100">
  </a>

<h3 align="center">Penterest</h3>

  <p align="center">
    비디오 Gif전환 및 Gif 검색 서비스
    <br />
  </p>
</div>



<br />

# 🤖 Introduction

🤹 Gif 생성 및 검색엔진
-  MoviePy를 활용한 동영상 Gif전환
-  OpenAI-CLIP모델을 활용한 Gif에 대한 설명 생성
-  ElasticSearch를 활용한 생성된 설명과 댓글에 대한 검색엔진

<div align="center">
    <img src="docs/book/.gitbook/assets/stack.gif">
</div>

# 🔋 Architecture
<img width="527" alt="화면 캡처 2023-08-22 133146" src="https://github.com/hufs0529/penterest/assets/81501114/69c15b7a-bd3c-4ab1-ba24-172e7cecb030">


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


# 
