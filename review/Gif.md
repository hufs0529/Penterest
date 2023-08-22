# Gif
Gif 게시물 생성, 삭제, 수정, 조회 및 검색엔진을 적용

## Gif 작성

### GifController POST gif/save
-  @Requestbody를 통해 댓글 작성 양식 ```GifSaveDto```를 통해 url, caption저장 요청


### GifService
- Controller로부터 받은 정보를 토대로 ```SecurityContextHolder```를 토대로 로그인된 email추출
- 추출된 email을 Gif의 ```confirmWriter```을 통해 저장

<br>

## Gif 삭제

### GifController DELETE gif/delete/{gifId}
-  @Pathvariable을 통해 삭제할 gifId 요청

### GifService
- controller로부터 전달받은 id 토대로 gif객체 불러오기
- ```checkAuthority(gif)```을 통해서 로그인 객체의 email과 gif의 작성자 email비교 후 일치하면 저장

<br>

## Gif 객체 정보 조회

### GifController GET gif/get/{gifId}
-  @Pathvariable을 통해 조회 gifId 요청

### GifService
- controller로부터 전달받은 id 토대로 gif객체 불오기
- ```GifInfoDto```를 통해 ```id```, ```url```, ```caption```, ```게시자 email```, ```commentList``` 불러오기

<br>


## 조건에 맞는 Gif 조회

### GifController GET gif/search
-  @RequestParam을 통해 ```sort(정렬 기준)```, ```page(노출될 페이지)```, ```size(페이지 당 노출될 게시글 수)```, @ModelAttribute를 통해 ```GifSearchCondition``` 요청
-  ```Pageable```을 정렬, 노출 페이지, 노출 게시물 선언

### GifService
- controller로부터 받은 정보를 토대로 ```GifRepository```가 아닌 ```CustomRepository``` 이용

### CustomGifRepository
- ```Page<Gif> search```에서 service에서 받은 정보를 토대로 ```TypeQuery```를 이용한 검색 쿼리 작

<br>


## Gif 작성자 email 조회

### GifController GET gif/getWriter/{gifId}
-  @PathVariable 통해 ```gifId``` 요청

### GifService
- controller로부터 받은 정보를 토대로 쿼리가 담긴 ```GifRepository``` 요청

### GifRepository
- ```@Query("SELECT g.writer.email FROM Gif g WHERE g.id = :gifId")``` 쿼리를 사용한 방법으로 불필요한 오버헤드 제거


<br>


## 작성자가 좋아요한 Gif 조회

### GifController GET gif/getWithLike/{email}
-  @PathVariable 통해 ```email``` 요청

### GifService
- ```LikedGifDto``` DTO사용으로 불필요한 정보 노출 방

### GifRepository
- ```@Query("SELECT NEW penterest.spring.domain.Like.dto.LikedGifDto(g.id, g.caption, g.url) " +
            "FROM Like l " +
            "JOIN l.gif g " +
            "JOIN l.member m " +
            "WHERE m.email = :email")``` 쿼리를 사용한 방법으로 DTO사용 및 ```Like```, ```Gif```, `Member``` entity join된 정보들로 정보 추출 

<br>
