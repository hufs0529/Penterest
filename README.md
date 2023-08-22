<br />
<div align="center">
  <a href="https://zenml.io">
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

<!-- TABLE OF CONTENTS -->
<details>
  <summary>🏁 Table of Contents</summary>
  <ol>
    <li><a href="#-introduction">Introduction</a></li>
    <li><a href="#-quickstart">Quickstart</a></li>
    <li>
      <a href="#-create-your-own-mlops-platform">Create your own MLOps Platform</a>
      <ul>
        <li><a href="##-1-deploy-zenml">Deploy ZenML</a></li>
        <li><a href="#-2-deploy-stack-components">Deploy Stack Components</a></li>
        <li><a href="#-3-create-a-pipeline">Create a Pipeline</a></li>
        <li><a href="#-4-start-the-dashboard">Start the Dashboard</a></li>
      </ul>
    </li>
    <li><a href="#-roadmap">Roadmap</a></li>
    <li><a href="#-contributing-and-community">Contributing and Community</a></li>
    <li><a href="#-getting-help">Getting Help</a></li>
    <li><a href="#-license">License</a></li>
  </ol>
</details>

<br />

# 🤖 Introduction

🤹 Penterest 서비스의 Gif 검색 기능을 위해서 동영상 업로드시 Gif변환 및 OpenAI-CLIP 모델을 사용한 Caption 생성
-  flask 서버를 통한 동영상 업로드
-  OpenAI-CLIP을 통한 Caption 생성 -> S3에 Gif적재
-  Spring 서버 적재를 위한 S3 Url과 Caption 반환

<div align="center">
    <img src="docs/book/.gitbook/assets/stack.gif">
</div>

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
</br>
OpenAI-CLIP 모델을 사용한 'CLIP_prefix_caption' 사용
</br>
</br>
<div>
<img width="180" alt="화면 캡처 2023-08-22 121932" src="https://github.com/hufs0529/penterest/assets/81501114/0cd9a49c-825d-40a9-aa1b-6e1049357718">
</div>
