import * as React from 'react';
import { CardMedia } from '@mui/material';

export default function UploadModalConverted(props) {

    const MediaStyle = {
        margin: 'auto'
    };

    return (
        <>
            <CardMedia
                sx={MediaStyle}
                component="img"
                alt={props.caption}
                src={props.gif}

            // 샘플 GIF
            // image='https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/sample.gif'
            // image='../images/GifSample2.gif'

            >
                {/* <img src="https://penterest.s3.ap-northeast-2.amazonaws.com/gifs/sample.gif" alt={caption} /> */}

            </CardMedia>

            {/* <div>{gif}{caption}</div> */}
            {/* <img src="../images/GifSample.gif"></img> */}

        </>
    )
}