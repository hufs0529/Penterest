<br />
<div align="center">
  <a href="https://zenml.io">
    <img alt="elk" src="https://github.com/hufs0529/penterest/assets/81501114/159ef7c8-d3ab-497a-b36c-9ea85e747013" alt="Logo" width="400" height="150">
  </a>

<h3 align="center">Search Engine with ELK</h3>

  <p align="center">
      MySQL데이터 적재하기
    <br />
  </p>
</div>



<br />

# 🤖 Introduction

🤹 Penterest 서비스의 Gif 검색 기능을 위해서 MySQL에 생성된 Gif의 Url, Caption데이터 검색 엔진 개발
-  docker, docker-compose를 활용한 ELK구축
-  Gif, Comment Entity를 활용한 검색엔진 구축을 위한 logstash-gif.conf, logstash-comment.conf 파이프라인 운영
-  Kibana를 활용한 데이터 시각화

<div align="center">
    <img src="docs/book/.gitbook/assets/stack.gif">
</div>

# 🔋 Architecture
<img width="705" alt="elk 아키" src="https://github.com/hufs0529/penterest/assets/81501114/968334f0-1693-417d-9dce-5f76bf05cc6a">


# 🤸 Quickstart

docker is required:

#### Docker를 활용한 빠른 시작
```bash
docker compose up -d
```
