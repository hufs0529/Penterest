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
  <img width="556" alt="erd" src="https://github.com/hufs0529/penterest/assets/81501114/be7e7d06-711a-406b-8112-ef85f7f04ca4">


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

# 🏇 API 명세서
- [Member](https://documenter.getpostman.com/view/25143450/2s9YJW66aG)
- [Gif](https://documenter.getpostman.com/view/25143450/2s9YJW66aJ)
- [Like](https://documenter.getpostman.com/view/25143450/2s9YJW66aD)
- [Follow](https://documenter.getpostman.com/view/25143450/2s9YJW66aC)
- [Comment](https://documenter.getpostman.com/view/25143450/2s9YJW66Vu)


# 🗺 Quickstart
#### 1. 이 브랜치에서는 Java 17, MySQL is required

#### 2. jar 파일 실행을 위해서 elasticsearch 서버가 필요합니다
[ELK 브랜치](https://github.com/hufs0529/penterest/tree/elk)

#### 3. Docker로 Spring 서버 시작하기
```bash
docker build -t penterest .
docker run penterest
```

#### 4. Gif 생성 및 AI를 위한 서버
[flask 브랜치](https://github.com/hufs0529/penterest/tree/flask)



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

<details>
<summary>Gif 생성</summary>
<div markdown="1">

```bash
@Override
    public Gif save(GifSaveDto gifSaveDto) { //GifServiceImpl
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername(); // 유저 정보 불러오기

        Gif gif = gifSaveDto.toEntity(); // gifSaveDto의 caption은 flask브랜치로부터 생성이 된다
        Member member = memberRepository.findByEmail(email);
        gif.confirmWriter(memberRepository, member);

        gifRepository.save(gif);
        return gif;
    }
```

</div>
</details>

<details>
<summary>Gif의 caption 생성</summary>
<div markdown="1">

```bash
@app.route("/upload", methods=['GET', 'POST'])
def upload_file():
  if request.method == 'POST':
    file = request.files['file']
    title = file.filename
    AWSs3.s3_put_video(s3, 'penterest', file, title)
    movie.make(title, 0, 5, 1,4)
    caption_txt = caption.inference(title, "COCO")
    print(caption_txt)
    
    data = {
      "url":AWSs3.s3_get_gif_url(title.replace("mp4","gif")),
      "caption":caption_txt
    }
    return data
```

</div>
</details>



### 3.검색엔진
#### 3-1. 전환된 Gif의 Caption 기반 ElasticSearch 검색엔진

<details>
<summary>Caption 검색 쿼리 설정</summary>
<div markdown="1">

```bash
public List<GifDocument> findByMatchesCaption(String caption) {  // GifSearchQueryRepository
        Criteria criteria = Criteria.where("caption").matches(caption);
        Query query = new CriteriaQuery(criteria);
        SearchHits<GifDocument> searchHits = operations.search(query, GifDocument.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
```

</div>
</details>

#### 3-2. 전환된 Gif의 Comment 기반 ElasticSearch 검색엔진

<details>
<summary>Comment 기 검색 쿼리 설정</summary>
<div markdown="1">

```bash
public List<CommentESDto> searchCommentsByContent(String content) {  // GifSearchRepository
        Criteria criteria = Criteria.where("content").matches(content);
        Query query = new CriteriaQuery(criteria);
        SearchHits<CommentDocument> searchHits = operations.search(query, CommentDocument.class);

        return searchHits.stream()
                .map(hit -> {
                    CommentDocument commentDocument = hit.getContent();
                    Long gifId = commentDocument.getGif_id();
                    Gif gif = null;
                    if (gifId != null) {
                        gif = gifRepository.findGifById(gifId);
                    }
                    return CommentESDto.fromCommentDocument(commentDocument, gif);
                })
                .collect(Collectors.toList());
    }
```

</div>
</details>

#### 3-3. jpql를 이용한 Gif 조회
<details>
<summary>jpql</summary>
<div markdown="1">

```bash
public Page<Gif> search(GifSearchCondition gifSearchCondition, Pageable pageable) { // CustomGifRepository
        String jpql = "select g from Gif g where 1=1";
        if (gifSearchCondition.getCaption() != null) {
            jpql += " and g.caption like :caption";
        }

        // Create query
        TypedQuery<Gif> query = em.createQuery(jpql, Gif.class);

        // Set parameters
        if (gifSearchCondition.getCaption() != null) {
            query.setParameter("caption", "%" + gifSearchCondition.getCaption() + "%");
        }

        // Pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Fetch results and total count
        List<Gif> content = query.getResultList();
        long total = getTotalCount(gifSearchCondition);

        return new PageImpl<>(content, pageable, total);
    }
```

</div>
</details>
<details>
<summary>pageable을 통한 검색 조건 설정</summary>
<div markdown="1">
  
```bash
@ResponseStatus(HttpStatus.OK)   // GifController
    @GetMapping("/search")
    public ResponseEntity search(
            @RequestParam(value = "sort", defaultValue = "createDate") String sort,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @ModelAttribute GifSearchCondition gifSearchCondition) {
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
        return ResponseEntity.ok(gifService.getGifList(pageable, gifSearchCondition));
    }
```

</div>
</details>

### 4. 게시글 좋아요
#### 4-1. 좋아요한 게시글 조회

<details>
<summary>GifRepository에 쿼리 생성 </summary>
<div markdown="1">

```bash
@Query("SELECT NEW penterest.spring.domain.Like.dto.LikedGifDto(g.id, g.caption, g.url) " +    // GifRepository
            "FROM Like l " +
            "JOIN l.gif g " +
            "JOIN l.member m " +
            "WHERE m.email = :email")
    List<LikedGifDto> findLikedGifDetailsByEmail(@Param("email") String email);
```

</div>
</details>

<details>
<summary>GifRepository에 쿼리 생성 </summary>
<div markdown="1">

```bash
@Query("SELECT NEW penterest.spring.domain.Like.dto.LikedGifDto(g.id, g.caption, g.url) " +    // GifRepository
            "FROM Like l " +
            "JOIN l.gif g " +
            "JOIN l.member m " +
            "WHERE m.email = :email")
    List<LikedGifDto> findLikedGifDetailsByEmail(@Param("email") String email);
```

</div>
</details>

<details>
<summary>email조회로 좋아요 한 게시글 불러오 </summary>
<div markdown="1">

```bash
@Override   // GifServiceImpl
    @Transactional(readOnly = true)
    public List<LikedGifDto> getLikeGifListWithEmail(String email) {
        List<LikedGifDto> likedGifDTOList = gifRepository.findLikedGifDetailsByEmail(email);
        return likedGifDTOList;
    }
```

</div>
</details>



### 5. Member 팔로우
#### 5-1. 팔로우, 팔로잉 관계

<details>
<summary>팔로우, 언팔로우 하기</summary>
<div markdown="1">

```bash
public String addFollow(String toAccount, String fromAccount) throws Exception { // FollowService
        if(Objects.equals(toAccount, fromAccount)) {
            throw new Exception();
        }

        Member toMember = memberRepository.findByEmail(toAccount);

        Member fromMember = memberRepository.findByEmail(fromAccount);

        Optional<Follow> relation = getFollowRelation(toMember.getEmail(), fromMember.getEmail());
        if(relation.isPresent()) {
            throw new Exception("Already exists");
        }
        followRepository.save(new Follow(toMember.getEmail(), fromMember.getEmail()));

        return fromAccount + " 가 " + toAccount + "를 팔로우하기 시작했습니다";
    }

    public String unFollow(String toAccount, String fromAccount) throws Exception {
        if(Objects.equals(toAccount, fromAccount)) {
            throw new Exception();
        }
        Member toMember = memberRepository.findByEmail(toAccount);

        Member fromMember = memberRepository.findByEmail(fromAccount);

        Optional<Follow> relation = getFollowRelation(toMember.getEmail(), fromMember.getEmail());
        if(relation.isEmpty()) {
            throw new Exception("No exists");
        }
        followRepository.delete(relation.get());

        return fromAccount + " 가 " + toAccount + "를 팔로우를 취소했습니다";
    }
    private Optional<Follow> getFollowRelation(String toAccount, String fromAccount) {
        return followRepository.findByToMemberAndFromMember(toAccount, fromAccount);
    }
```

</div>
</details>


#### 5-2. 팔로우, 팔로잉

<details>
<summary>팔로잉, 팔로워 멤버 리스트 확인</summary>
<div markdown="1">

```bash
public List<Follow> findFollowingMembers(String email) {// FollowService
        List<Follow> following = new ArrayList<>();
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            following = followRepository.findByFromMember(member.getEmail());
        }
        return following;
    }


    public List<Follow> findFollowerMembers(String email) {
        List<Follow> following = new ArrayList<>();
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            following = followRepository.findByToMember(member.getEmail());
        }

        return following;
    }
```

</div>
</details>

<details>
<summary>팔로잉, 팔로워 수 확인</summary>
<div markdown="1">

```bash
public Long getFollowingCount(String email) {
        List<Follow> response = findFollowerMembers(email);
        return (long) response.size();
    }

    public Long getFollowerCount(String email) {
        List<Follow> response = findFollowerMembers(email);
        return (long) response.size();
    }
```

</div>
</details>


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

