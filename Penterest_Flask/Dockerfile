FROM python:3.7

WORKDIR /app

COPY package.json .

COPY requirements.txt .
RUN pip install -r requirements.txt

RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx

COPY . .

EXPOSE 5000

CMD [ "python", "f.py" ]