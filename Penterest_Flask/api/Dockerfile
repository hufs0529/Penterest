FROM python:3.7

WORKDIR /app

COPY package.json .

COPY requirements.txt .
RUN pip install -r requirements.txt

RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

COPY . .

EXPOSE 5000

# token설정
ENV REPLICATE_API_TOKEN=r8_1o2kBqukSwiAOQwv2wklxkxzxW4jG7x4e9K2F

CMD [ "python", "f.py" ]
