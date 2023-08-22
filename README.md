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

<h3 align="center">Video to Gif Converter & Save in S3</h3>

  <p align="center">
    Video -> Gif 변환 및 S3 적재 API
    <br />
  </p>
</div>



<br />

# 🤖 Introduction

🤹 Penterest 서비스의 Gif 검색 기능을 위해서 동영상 업로드시 Gif변환 및 OpenAI-CLIP 모델을 사용한 Caption 생성
-  flask 서버를 통한 동영상 업로드
-  OpenAI-CLIP을 통한 Caption 생성 -> S3에 Gif적재
-  Spring 서버 적재를 위한 S3 Url과 Caption 반환

<div align="center">
    <img src="docs/book/.gitbook/assets/stack.gif">
</div>

# 🔋 Architecture
<img width="590" alt="flask아키" src="https://github.com/hufs0529/penterest/assets/81501114/4c7ab6fb-3d91-4e29-83a3-4be322c59ea2">




# 🤸 Quickstart

Python 3.7 is required:

#### 1. 서버에 CLIP모델 직접 사용하기 
```bash
penterest/Penterest_Flask/model/Dockerfile
```
#### 2. CLIP API 사용하기
```bash
penterest/Penterest_Flask/api/Dockerfile
```

# 🖼️ About Model
<div>
  <img src="https://github.com/hufs0529/penterest/assets/81501114/b736a7f4-43ca-47cb-ba82-14aea7a6f897" width="10%" height="10%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>
https://github.com/rmokady/CLIP_prefix_caption
</div>
</br>
</br>
OpenAI-CLIP 모델을 사용한 'CLIP_prefix_caption' 사용
</br>
</br>
<div>
<img width="180" alt="화면 캡처 2023-08-22 121932" src="https://github.com/hufs0529/penterest/assets/81501114/0cd9a49c-825d-40a9-aa1b-6e1049357718">
</div>


# 
