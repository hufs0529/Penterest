import * as React from 'react';
import { CardMedia } from '@mui/material';

export default function UploadModalPreview(props) {

    const MediaStyle = {
        margin: 'auto'
    };

    return (
        <>
            <CardMedia
                sx={MediaStyle}
                component='video'
                autoPlay
                muted
                loop
                src={props.file.url}

            >
            </CardMedia>

        </>
    )
}