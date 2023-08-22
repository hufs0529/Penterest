# Follow
다른 계정 Follow

## 팔로우

### FollowController POST follow/{toAccount}/{fromAccount}
- toAccount, fromAccount를 @PathVariable로 service에 요청

### FollowService
- getFollowRelation(toAccount, fromAccount)을 통해서 팔로우 관계 파악
- 팔로우 관계일시 toMember, fromMember을 이용한 Follow entity 생성

<br>

## 언팔로우

### FollowController POST unfollow/{toAccount}/{fromAccount}
- toAccount, fromAccount를 @PathVariable로 service에 요청

### FollowService
- getFollowRelation(toAccount, fromAccount)을 통해서 팔로우 관계 파악
- 팔로우 관계일시 toMember, fromMember을 이용한 Follow entity 삭

<br>

## 팔로워 리스트 조회

### FollowController GET follower/{account}
- account를 @PathVariable로 service에 요청

### FollowService
- 요청된 account(email)을 기준으로 repository의 findByToMember을 이용해서 팔로워 리스트 조회

### FollowRepository
- findByToMember에 ``` @Query(value = "select f from Follow f where f.toMember = :fromMember")```를 이용함으로써 팔로워 리스트 반환

<br>

<br>

## 팔로잉 리스트 조회

### FollowController GET following/{account}
- account를 @PathVariable로 service에 요청

### FollowService
- 요청된 account(email)을 기준으로 repository의 findByFromMember을 이용해서 팔로워 리스트 조회

### FollowRepository
- findByToMember에 ``` @Query(value = "select f from Follow f where f.fromMember = :toMember")```를 이용함으로써 팔로잉 리스트 반환

<br>

## 팔로워 수 조회

### FollowController GET getFollowerCount/{account}
- account를 @PathVariable로 service에 요청

### FollowService
- 팔로워 리스트를 조회할때 사용되었던 ```findFollowerMembers()``` 의 size반환


<br>

## 팔로 수 조회

### FollowController GET getFollowingCount/{account}
- account를 @PathVariable로 service에 요청

### FollowService
- 팔로잉 리스트를 조회할때 사용되었던 ```findFollowingMembers()``` 의 size반환

