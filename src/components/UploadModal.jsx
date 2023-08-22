import { useState, useEffect } from 'react'
import * as React from 'react';
import { Box, Button, Card, Modal, FormControl, FormLabel, ButtonGroup, Slider, Tooltip } from '@mui/material';
import AddAPhotoIcon from '@mui/icons-material/AddAPhoto';
import UploadModalPreview from './UploadModalPreview';
import UploadModalConverted from './UploadModalConverted';
import { saveAs } from 'file-saver';
import axios from "axios";

// 시간지정 관련 전역함수 및 변수
function timestamp(time) {
    return `${time}`;
}

export default function UploadModal(props) {

    // 업로드 모듈
    axios.defaults.withCredentials = true;

    // const inputFile = document.getElementById("file");
    //const inputVIdeo = document.getElementById("video");
    const [file, setFile] = useState({});
    const [video, setVideo] = useState("");
    const [gif, setGif] = useState("");
    const [caption, setCaption] = useState("");
    const [fileChanged, setFileChanged] = useState(false);
    const [gifSpeed, setGifSpeed] = useState(true);

    useEffect(() => {
        if (fastButtonVariant === 'contained' && slowButtonVariant === 'outlined'){
            setGifSpeed(true);
        }        
        else if(fastButtonVariant === 'outlined' && slowButtonVariant === 'contained'){
            setGifSpeed(false);
        }
        else{
            setGifSpeed(true);
        }
    })


    // 
    const changeVideo = e => {
        // setVideo(() => e.target.files[0]);
        // const videoUrl = URL.createObjectURL(video);
        // console.log(videoUrl);
        // inputVIdeo.setAttribute("src", videoUrl);
        // inputVIdeo.play();
        // const file = e.target.files[0];
        // setVideo(file);
        // console.log(video);
        setFileChanged(true);
        setVideo(e.target.files[0])
        const videoTpye = e.target.files[0].type.includes('video');
        setFile({
            url: URL.createObjectURL(e.target.files[0]),
            video: videoTpye,
        })
    }

    const submitVideo = e => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", video);
        formData.append("start", time[0]);
        formData.append("end", time[1]);
        formData.append("speed", gifSpeed);
        console.log(video);

        if (fileChanged) {
            setFileChanged(false);
            setGif('https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/tenor.gif');
            setCaption('');

            const config = {
                "Content-Type": 'application/json',
                withCredentials: true
            };

            axios
                .post("http://localhost:5000/upload", formData, config)
                .then(res => {
                    setGif(res.data[1]);
                    setCaption(res.data[0]);
                })
            console.log(gif);
            console.log(caption);
        }
    }

    const submitData = e => {
        e.preventDefault();
        const createdDate = new Date();
        const id = 0;
        
        const JsonForDB = {
            caption: caption,
            gifUrl: gif,
            createdDate: createdDate,
            id: id,
        };

        const config = {
            "Content-Type":"application/json",
            withCredentials:true,
        };
        axios
        .post("http://localhost:8080/api/gifs", JsonForDB, config)
        .then((res) => {
            alert("성공");
            console.log(res.data);
        })
        .catch((error) => {
            console.log("실패");
            console.log(error);
        });
    };



    // 이미지 저장 함수
    //URL로부터 다운로드 받는 기능: 'file-saver' 패키지 다운로드 필요
    const onDownload = () => {
        //첫 번째 인수: 가져올 URL, 두 번째 인수: 저장이름 및 형식 
        saveAs(`${gif}`, `${file.url}.gif`);
    }

    // 느리게, 뻐르게 버튼변환 관련 함수
    // 느리게 버튼
    const [slowButtonVariant, setSlowButtonVariant] = useState('outlined');

    const handleSlowButtonChange = () => {
        if (fastButtonVariant === 'outlined' && slowButtonVariant === 'outlined') {
            setSlowButtonVariant('contained');
        }
        else {
            setSlowButtonVariant('outlined');
        }
    }

    // 빠르게 버튼
    const [fastButtonVariant, setFastButtonVariant] = useState('outlined');

    const handleFastButtonChange = () => {
        if (slowButtonVariant === 'outlined' && fastButtonVariant === 'outlined') {
            setFastButtonVariant('contained');
        }
        else {
            setFastButtonVariant('outlined');
        }
    }

    // 시간지정 관련 함수
    const [time, setTime] = React.useState([0, 5]);

    const handleTimeChange = (event, newTime) => {
        setTime(newTime);
    };

    // 보여주기창-GIF생성창 전환 함수
    // Preview 스위치
    const [previewShown, setPreviewShown] = useState(true);

    const handlePreview = event => {
        setPreviewShown(false);
    }

    // 스프링전송, 저장하기 컨트롤 함수
    const [rightButtons, setRightButtons] = useState(true);
    const handlerightButtons = () => {
        if (previewShown === true) {
            setRightButtons(false);
        } else {
            setRightButtons(true);
        }
    }

    // 모달창 모듈
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',

        // width: '99%',
        // maxWidth: '600px',
        // height: '80vh',
        width: '640px',
        height: '600px',

        bgcolor: 'background.paper',
        border: '1px transperant',
        boxShadow: 24,
        p: 2,
        borderRadius: '20px',

        // 임시 저장용 수치: 완성본에서는 지울 것
        // display: 'flex',
        // width: '100%',
        // justifyContent: 'space-between',
    };

    const CardStyle = {
        // cover를 넣으니까 중앙정렬이 됨: 이유는 모르겠다
        display: 'cover',
        width: '100%',
        // height: '70vh',
        height: '480px',

        bgcolor: '#ddd',
        border: '1px dashed #000',
        mt: 2
    };

    const MediaStyle = {
        margin: 'auto'
    };

    return (
        <>
            <Button
                variant='text'
                onClick={handleOpen}
                style={{
                    flex: '0 0 auto',
                    marginLeft: "10px",
                    marginRight: "10px",
                    fontWeight: "bold"
                }}
            >
                {/* 이미지 업로드 */}
                <Tooltip title="이미지 업로드">
                    <AddAPhotoIcon
                        color='action'
                        fontSize='large'
                    />
                </Tooltip>
            </Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Box sx={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        mb: '10px'
                    }}>
                        <form
                            onSubmit={submitVideo}
                            encType="multipart/form-data"
                            style={{ width: '50%' }}
                        >
                            <ButtonGroup>
                                <input
                                    id="file"
                                    type="file"
                                    accept="video/mp4,video/mkv, video/x-m4v,video/*"
                                    onChange={changeVideo}
                                    style={{ width: '70%', display: 'none' }}
                                />
                                <Button>
                                    <label for="file">
                                        파일선택
                                    </label>
                                </Button>
                                <Button
                                    type='submit'
                                    onClick={() => { handlePreview(); handlerightButtons(); }}
                                >
                                    GIF 생성
                                </Button>
                            </ButtonGroup>
                        </form>
                        <ButtonGroup disabled={rightButtons}>
                            {/* <ButtonGroup> */}
        
                            <form onSubmit={submitData}>
                                <Button type="submit"> 전송 </Button>
                            </form>
                            <Button onClick={onDownload}> 저장 </Button>
                        </ButtonGroup>
                    </Box>

                    <Box>
                        <FormControl sx={{ width: '30%' }}>
                            <FormLabel id="play-speed-buttons-group"> 재생속도 </FormLabel>
                            <ButtonGroup size='small'>
                                <Button
                                    variant={slowButtonVariant}
                                    onClick={handleSlowButtonChange}
                                >
                                    느리게
                                </Button>
                                <Button
                                    variant={fastButtonVariant}
                                    onClick={handleFastButtonChange}
                                >
                                    빠르게
                                </Button>
                            </ButtonGroup>
                        </FormControl>
                        <FormControl sx={{ width: '68%' }}>
                            <FormLabel id="demo-controlled-radio-buttons-group">
                                시간지정
                                {/* 1번 함수 적용시 활성화 */}
                                {/* <span
                                    style={{
                                        color: 'black',
                                        fontWeight: 'bolder',
                                        marginLeft: '10px'
                                    }}
                                >
                                    {formatDuration(position[0])} ~ {formatDuration(position[1])}
                                </span> */}
                            </FormLabel>
                            {/* 1번 함수 */}
                            {/* <Slider
                                getAriaLabel={() => 'Time range'}
                                value={position}
                                onChange={handleTimeChange}
                                valueLabelDisplay="off"
                                // getAriaValueText={timestamp}
                                min={0}
                                step={1}
                                max={duration}
                                disableSwap
                            /> */}

                            {/* 2번 함수 */}
                            <Slider
                                getAriaLabel={() => 'Time range'}
                                value={time}
                                onChange={handleTimeChange}
                                valueLabelDisplay="auto"
                                // getAriaValueText={timestamp}
                                min={0}
                                max={10}
                            // min={0}
                            // step={1}
                            // max={30}
                            // scale={calculateTime}
                            />
                        </FormControl>
                    </Box>


                    <Card sx={[CardStyle, { mt: 2 }]}>
                        {previewShown && (
                            <UploadModalPreview
                                gif={gif}
                                caption={caption}
                                changeVideo={changeVideo}
                                submitVideo={submitVideo}
                                file={file}
                            />
                        )}

                        <UploadModalConverted

                            caption={caption}
                            gif={gif}
                        />

                    </Card>

                </Box>
            </Modal>
        </>
    )
}