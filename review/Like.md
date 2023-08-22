# Like
Gif 게시물에 대한 좋아요, 좋아요 취소

## 좋아요

### LikeController
- ```memberId```,  ```gifId``` 형식인 **LikeRequestDto** 정보로  @RequestBody 요청


### LikeService
- controller에게 **LikeRequestDto** 전달 받음
- member 혹은 gif가 존재하지 않는다면 예외처리 발생
- Controller에게 받은 memberId, gifId를 토대로 ```Like Entity``` 생성
- checkAuthority를 통해 로그인 정보 조회를 통해 로그인한 유저가 맞는지 인증


<br>

## 좋아요 취소

### LikeController

- ```memberId```,  ```gifId``` 형식인 **LikeRequestDto** 정보로  @RequestBody 요청


### LikeService
- controller에게 **LikeRequestDto** 전달 받음
- member 혹은 gif가 존재하지 않는다면 예외처리 발생
- Controller에게 받은 memberId, gifId를 토대로 ```Like Entity``` 생성
- checkAuthority를 통해 로그인 정보 조회를 통해 로그인한 유저가 맞는지 인증



