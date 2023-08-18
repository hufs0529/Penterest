import replicate
import requests

def inference(gif):
    # S3 파일의 URL
    s3_url = "https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/{gif}".replace("mp4","gif")
    local_file_path = "downloaded_file.gif"

    # S3 파일 다운로드
    response = requests.get(s3_url)
    with open(local_file_path, "wb") as file:
        file.write(response.content)

    # replicate 라이브러리 사용
    output = replicate.run(
        "rmokady/clip_prefix_caption:9a34a6339872a03f45236f114321fb51fc7aa8269d38ae0ce5334969981e4cd8",
        input={"image": open(local_file_path, "rb")}
    )

    # 결과 출력
    print(output)

    # 다운로드한 파일 삭제 (선택 사항)
    import os
    os.remove(local_file_path)
