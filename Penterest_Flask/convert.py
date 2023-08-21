import os
import requests
import replicate

def inference(gif):
    # S3 파일의 URL
    s3_url = f"https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/{gif}".replace("mp4","gif")
    print(s3_url)
    local_file_path = "downloaded_file.gif"

    # S3 파일 다운로드
    response = requests.get(s3_url)
    with open(local_file_path, "wb") as file:
        file.write(response.content)

    # 환경 변수에서 API 토큰 가져오기
    api_token = os.environ.get('REPLICATE_API_TOKEN')
    print(api_token)
    if not api_token:
        raise ValueError("REPLICATE_API_TOKEN not found in environment variables.")
    print("output 직전")
    # replicate 라이브러리 사용
    output = replicate.run(
        "rmokady/clip_prefix_caption:9a34a6339872a03f45236f114321fb51fc7aa8269d38ae0ce5334969981e4cd8",
        input={"image": open(local_file_path, "rb")},
        api_token=api_token  # 환경 변수에서 가져온 API 토큰 사용
    )

    print("output 직후")
    # 결과 출력
    print(output)

    # 다운로드한 파일 삭제 (선택 사항)
    os.remove(local_file_path)
    return output

