# ElasticSearch
ElasticSearch를 이용한 Gif 검 

## Caption으로 Gif 조회

### GifController GET gif/searchByCaption/{caption}
- @Pathvariable로 검색어 ```caption```을 받아 service로 검색 요청


### GifService
- Controller로부터 받은 정보를 토대로 ```gifSearchQueryRepository``` 요청

### GifSearchQueryRepository
- NoSQL 객체인 ```GifDocument``` 검색을 위한 repository이다
- ```Criteria```를 이용해서 ```caption```과 match되는 쿼리 결과 반환

<br>

## Comment으로 Gif 조회

### GifController GET gif/searchByComment/{comment}
- @Pathvariable로 검색어 ```comment```을 받아 service로 검색 요청


### GifService
- Controller로부터 받은 정보를 토대로 ```gifSearchQueryRepository``` 요청

### GifSearchQueryRepository
- NoSQL 객체인 ```CommentDocument``` 검색을 위한 repository이다
- ```GifESDto```를 통해서 
- ```Criteria```를 이용해서 ```comment```과 match되는 쿼리 결과 반환
- ```CommentDocument``` 가 존재하지만 ```comment``` 검색시 ```Gif```이 노출되는 쿼리를 짜고 싶다 
-  ```CommentESDto``` DTO에 ```GifESDto``` DTO를 담아줌으로써 ```comment``` 검색시 ```Gif```노출 가능

<br>

## Logstash를 사용하지 않는 MySQL -> ElasticSearch 로

### PostMapping POST gif/migragte
- service에 post 요청

### GifService
- 모든 Gif 조회 후 ```GifInfoDtoToGifDocumentConverter```의 ```converter```를 통해서 Gif entity -> GifDocument 적재
