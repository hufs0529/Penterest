import boto3
import movie
import AWSs3
from flask import Flask, request, jsonify, send_file, render_template
from werkzeug.utils import secure_filename
from moviepy.editor import *
from flask_cors import CORS
import convert, json
import requests 

app = Flask(__name__)
CORS(app, supports_credentials=True)

s3 = AWSs3.s3_connection()

@app.route("/upload", methods=['GET', 'POST'])
def upload_file():
  if request.method == 'POST':
    file = request.files['file']
    title = file.filename
    AWSs3.s3_put_video(s3, 'penterest', file, title)
    movie.make(title, 0, 5, 1.4)
    caption_txt = convert.inference(title)
    print(caption_txt)

    data = {
      "url":AWSs3.s3_get_gif_url(title.replace("mp4","gif")),
      "caption":caption_txt
    }
    return data
    # data = {
    #   "url": "www.GOOGLE.com",
    #   "caption": "hello"
    # }


    # spring_url = "http://localhost:8080/gif/save"
    # headers = {'Content-Type': 'application/json'}

    # response = requests.post(spring_url, json=data, headers=headers)
    # print(json.dumps(data))
    # if response.status_code == 201 or 200:
    #   spring_data = response.json()
    #   return jsonify(spring_data)
    # else:
    #   return response.status_code


if __name__ == '__main__':
  app.run(host="0.0.0.0", port="5000", debug=True)