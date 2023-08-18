from moviepy.editor import *
from moviepy.video.fx.all import speedx
import AWSs3
import io
import numpy as np

s3 = AWSs3.s3_connection()
#gif_path = "https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/"

def make(title, start, end, speed):
  gs = 1.6
  if speed == True:
    gs = 1.6
  elif speed == False:
    gs = 0.7
  video = VideoFileClip(f"https://penterest.s3.ap-northeast-2.amazonaws.com/videos/{title}", audio = False)
  sub_video = video.subclip(start, end)
  video = speedx(sub_video, gs)
  # 해상도
  video = video.resize(width=360)

  frames = np.array([frame for frame in video.iter_frames()])
  gif_file = io.BytesIO()
  imageio.mimsave(gif_file, frames, format='gif', duration=1000 / 12, palettesize=196)
  gif_file.seek(0)
  
  s3.put_object(
      Body=gif_file,
      Bucket='penterest',
      Key=f'gifs/{title}'.replace("mp4","gif"),
      ContentType='image/gif',
    )
  
make('hi', 0, 5, 1.4)