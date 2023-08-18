import boto3

def s3_connection():
  try:
    s3 = boto3.client(
      service_name="s3",
      region_name="ap-northeast-2",
      aws_access_key_id='',
      aws_secret_access_key=''
    )
  except Exception as e:
    print(e)
  else:
    print("s3 bucket connected!")
    return s3

def s3_put_video(s3, bucket, file, filename):
  try:
    file_content = file.read()
    s3.put_object(
      Body=file_content,
      Bucket=bucket,
      Key=f'videos/{filename}',
      ContentType=file.content_type,
    )
  except Exception as e:
    print(e)
    return False
  return True

def s3_put_gif(s3, bucket, file, filename):
  try:
    s3.put_object(
      Body=file,
      Bucket=bucket,
      Key=f'gifs/{filename}',
      ContentType=file.content_type,
    )
  except Exception as e:
    return False
  return True

def s3_get_video_url(filename):
  return f"https://penterest.s3.ap-northeast-2.amazonaws.com/videos/{filename}"
   
def s3_get_gif_url(filename):
  return f"https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/{filename}"
   
