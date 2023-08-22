import boto3
import movie
import AWSs3
from flask import Flask, request
from moviepy.editor import *
from flask_cors import CORS
import convert
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


if __name__ == '__main__':
  app.run(host="0.0.0.0", port="5000", debug=True)