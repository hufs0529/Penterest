# Comment
Gif 게시물에 대한 댓글 생성, 조회, 수정, 삭제 및 게시자가 작성한 Comment 조회

## 댓글 작성

### CommentController POST comment/save/{gifId}
- 댓글을 남길 Gif을 @Pathvariable ```gifId```로,   @Requestbody를 통해 댓글 작성 양식 ```CommentSaveDto```


### CommentService
- Controller로부터 받은 정보를 토대로 ```loggedInUserEmail```과 회원정보가 일치할시 저장
- ```confirmWriter```를 활용해서 comment의 작성자 추가


<br>

## 대댓글 작성

### CommentController POST comment/save/{gifId}/{commentId}
- 댓글을 남길 Gif을 @Pathvariable ```gifId```로,  대댓글을 남길 Comment를 @Pathvariable ```commentId```로, @Requestbody를 통해 댓글 작성 양식 ```CommentSaveDto```


### CommentService
- Controller로부터 받은 정보를 토대로 ```loggedInUserEmail```과 회원정보가 일치할시 저장
- 대댓글의 경우 ```confirmParent```를 통해서 댓글의 상위댓글 추가


<br>

## 댓글 수정

### CommentController POST comment/update/{commentId}
- 수정할 Comment를 @Pathvariable ```commentId```로, @Requestbody를 통해 댓글 작성 양식 ```CommentUpdateDto```

### CommentService
- comment의 작성자와 로그인된 사용자의 email이 동일하거나 ADMIN계정시 수정 가능

<br>

## 댓글 삭제
##### 대댓글의 상위 댓글이 지워질 경우 하위 댓글 모두 삭제

### CommentController POST comment/remove/{commentId}
- 삭제 Comment를 @Pathvariable ```commentId```로 받

### CommentService
- comment의 작성자와 로그인된 사용자의 email이 동일하거나 ADMIN계정시 삭제 가능
- Comment의 ```finidRemovableList()```에 해당하는 댓글들 삭제 -> 상위 댓글 삭제시 하위 댓글 삭제

<br>

## 특정 게시자의 모든 댓글 조회

### CommentController POST comment/commentListByWriter/{email}
-  조회할 email을 @Pathvariable로 제시해준다

### CommentRepository
- ```@Query("SELECT m.commentList FROM Member m WHERE m.email = :email")```를 사용해서 Comment 객체에 존재하는 email을 Member의 email과 비교 조회
