# Account

계정을 생성 및 삭제,수정하고 로그인을 처리한다.

## createAccount 계정 생성

### AccountController

- ```email```,  ```password``` 4가지 형식인 **MemberSignUpDto**로 회원 가입 진행
- ```email```이 **기존 회원들과 중복되지 않는다면** 해당 형식으로 가입 가능
- service에게 **MemberSignUpDto** 전달

### MemberService

- controller에게 **MemberSignUpDto** 전달 받음
- MemberSignUpDto를 ```Member entity``` 로 변경후 ```MemberRepository``` 에게 전달
- ```MemberRepository``` 에 저장되면 Controller에게 반환

<br>

## login 로그인

### AccountController

- ```email```, ```password``` 2가지 형식인 LoginDto 로 로그인 진행
- ```loginId```에는 ```username(활동 아이디)``` 만 입력 가능

### MemberService

```회원정보``` 가 일치한다면 Token 반환
- 비밀번호가 다를경우 ```UserException``` 발생

<br>

## deleteAccount 계정 삭제

### AccountController & UserService

- ```username(활동 아이디)``` 으로 계정 삭제 
- 해당 계정과 관련된 ```follower```, ```following``` 관계를 모두 삭제
- 해당 계정의 ```post(게시물)``` 을 모두 삭제
