import { useState } from 'react'
import React from 'react';
import { Box, Button, Card, Modal } from '@mui/material';
import { saveAs } from 'file-saver';

export default function ImageDetail(props) {
    const [open, setOpen] = useState(false);
    const [gifUrl, setGifUrl] = useState("");
    const handleOpen = () => setOpen(true);
    const handleClose = () => {
        setOpen(false);
    }

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        border: '0px solid #000',

    };

    const CardStyle = {
        width: '100%',
        height: 'fit-content',
        bgcolor: '#ddd',
        border: '1px sprite #000',
    };

    //URL로부터 다운로드 받는 기능: 'file-saver' 패키지 다운로드 필요
    const onDownload = async () => {
        //첫 번째 인수: 가져올 URL, 두 번째 인수: 저장이름 및 형식 
        console.log(`${props.item._source.gif_url}`)
        saveAs(`${props.item._source.gif_url}`, `${props.item._source.gif_url}`);
        console.log(props.item._source.gif_url);
    }


    return (
        <>

            {/* 갤러리 창에서 이미지 표시되는 부분 */}
            <img
                src={`${props.item._source.gif_url}?w=162&auto=format`}
                srcSet={`${props.item._source.gif_url}?w=162&auto=format&dpr=2 2x`}
                alt={props.item.title}
                loading="lazy"
                style={{
                    borderBottomLeftRadius: 4,
                    borderBottomRightRadius: 4,
                    display: 'block',
                    width: '100%',
                }}
                onClick={handleOpen}
            />

            {/* 모달창 */}
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>

                    <Card sx={[CardStyle, { mr: 0 }]}>
                        <img
                            src={props.item._source.gif_url}
                            srcSet={props.item._source.gif_url}
                            alt={props.item.title}
                            loading="lazy"
                            style={{
                                borderBottomLeftRadius: 4,
                                borderBottomRightRadius: 4,
                                display: 'block',
                                width: '100%',
                                height: '100%',
                                maxWidth: '90vh',
                                maxHeight: '90vh',
                                objectFit: 'contain'
                            }}
                        />
                    </Card>

                    <Box sx={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        alignItems: 'center'
                    }}>

                        <Button
                            variant='text'
                            onClick={onDownload}
                            sx={{
                                borderTopleftRadius: 0,
                                '&:hover': {
                                    fontWeight: 'bold',
                                },
                            }}>
                            저장
                        </Button>

                        <Button
                            variant='text'
                            onClick={handleClose}
                            sx={{
                                fontWeight: 'bold',
                                borderTopRightRadius: 0,
                                '&:hover': {
                                    color: '#fff',
                                    backgroundColor: '#222',
                                },
                            }}>
                            닫기
                        </Button>
                    </Box>

                </Box>
            </Modal>

        </>
    )
}
