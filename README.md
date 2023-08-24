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

  <h2 align="center">
    비디오 Gif전환 및 Gif 검색 서비스
    <br />
    <br />
    <br />
  </h2>
  <h3>Role: Spring API, Flask 모델 적재, S3, ELK, React(X)</h3>
    
  
</div>



<br />

### 🙌  시연 영상: https://youtu.be/5-_bcY2WcaE

# 🤖 ERD
  <img width="556" alt="erd" src="https://github.com/hufs0529/penterest/assets/81501114/a2c1d8cc-0b22-44c3-9581-8c2101af9e41">


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


# 🗺 Quickstart

Java 17, MySQL is required

#### 1. Docker로 시작하기
```bash
docker build -t penterest .
docker run penterest
```

# 🤸 Readme & branches
### branches
- [Gif변환 및 AI서버](https://github.com/hufs0529/penterest/tree/flask)
- [ELK](https://github.com/hufs0529/penterest/tree/elk)

### Readme
- [Member](https://github.com/hufs0529/penterest/blob/main/review/Member.md)
- [Gif](https://github.com/hufs0529/penterest/blob/main/review/Gif.md)
- [ElasticQuery](https://github.com/hufs0529/penterest/blob/main/review/ElasticQuery.md)
- [Comment](https://github.com/hufs0529/penterest/blob/main/review/Comment.md)
- [Like](https://github.com/hufs0529/penterest/blob/main/review/Like.md)
- [Follow](https://github.com/hufs0529/penterest/blob/main/review/Follow.md)


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


# 🏇 Remarkable Points

#### 1. Gif 업로드시 로그인된 사용자 email추출 후 게시자 저장
```bash
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
```
#### 2. Member 삭제 등 Member본인 혹인 ADMIN계정시 삭제 권한 부여
```bash
if (checkAuthority(gif) || gif.getWriter().getRole().equals("NORMAL")) {
            gifRepository.delete(gif);
```
#### 3. DTO 사용으로 Gif 조회시 Member의 email만 노출시켜서 개인정보 및 불필요한 정보 노출 방지

#### 4. 댓글 삭제시 대댓글 자동 삭제
```bash
public List<Comment> findRemovableList() {
        List<Comment> result = new ArrayList<>();
        Optional.ofNullable(this.parent).ifPresentOrElse(
                parentComment -> {
                    if(parentComment.isRemoved() && parentComment.isAllChildRemoved()) {
                        result.addAll((parentComment.getChildList()));
                        result.add(parentComment);
                    }
                },
                () -> {
                    if (isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildList());
                    }
                }
        );
        return result;
    }
```
# 🏇 UI & 시스템 로직

### 기본 UI
- 상단에 검색엔진
- 하단에 검색된 결과 이미지
      ![검색된 이미지](https://github.com/hufs0529/penterest/assets/81501114/8320c54c-e5bf-493f-aac4-9935cb3d2f0b)
- 업로드 모달창에서 변환될 GIF의 재생속도, 구간 지정 가능
    ![업로드 모달 (1)](https://github.com/hufs0529/penterest/assets/81501114/bfe12fbc-fe32-47ee-b7a9-7a3d6cc6166c)

### Data Flow
1. Flask 서버에서 GIF 변환 및 S3 스토리지 저장
2. 변환된 GIF를 OpenAI-CLIP 모델로 이미지에 대한 설명 생성
   ##### 2-1. Flask → React로 S3에 저장된 GIF의 url과 이미지에 대한 설명이 response형태로 반환된다
     <img width="474" alt="화면 캡처 2023-08-22 121932" src="https://github.com/hufs0529/penterest/assets/81501114/35fca35f-54f2-4644-9f26-4ef4c947d828">
3. Spring 서버로 데이터 전송 및 MySQL 적재
     ![GCP MYSQL](https://github.com/hufs0529/penterest/assets/81501114/07c1b162-8c2b-4de1-ae12-642be3185b00)
4. Logstash를 활용한 MySQL -> ElasticSearch 적재
     ##### 4-1. 검색을 위한 Tokenizer, Filter적용
5. React Web UI에서 검색엔진 구현
   <p align="center">
   <h5>sitting</h5>
  <img src="https://github.com/hufs0529/penterest/assets/81501114/4500e255-f4ae-49a3-8434-8f2d7f3c967c" alt="sitting PNG" width="300" />
  <h5>sits</h5>
  <img src="https://github.com/hufs0529/penterest/assets/81501114/8dc7ac39-3e2c-4b6a-86b3-5872ca09d0c9" alt="sit PNG" width="300" />
  <h5>sit</h5>
  <img src="https://github.com/hufs0529/penterest/assets/81501114/0fb88231-7bf9-406d-8cec-6d7228c874a7" alt="sits PNG (1)" width="300" />
</p>

