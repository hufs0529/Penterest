// import { Link } from "react-router-dom";
import * as React from 'react';
import { useState, useLayoutEffect, useEffect } from 'react';
import { Box, Paper } from '@mui/material';
import MUILink from '@mui/material/Link';
import Masonry from '@mui/lab/Masonry';
import { styled } from '@mui/material/styles';
import ImageDetail from './ImageDetail';
import axios from "axios";
import Nav from './Nav';

export default function GalleryMasonry() {

    const [value, setValue] = useState("");
    const [itemData, setItemData] = useState([]);
    
    useEffect(() => {
        const config = {"Content-Type": 'application/json',
                      withCredentials: true};

        axios.put('http://localhost:9200/penterest/_settings', {
           "analysis": {
              "analyzer": {
                "my_custom_analyzer": {
                "type": "custom",
                "tokenizer": "letter",
                "filter": ["lowercase", "stop", "snowball"]
                 }
               }
             }
          })

        axios.post('http://localhost:9200/penterest/_search', {
        query: {
            match: {
            caption: value
            }
          }
        }, config)
        .then(function (response) {
            //console.log(response.data)
            //setItemData(['https://images.unsplash.com/photo-1518756131217-31eb79b20e8f'])
            setItemData([...response.data.hits.hits])
            console.log(response.data.hits.hits)
        })
        .catch(function (error) {
          console.log(error);
        });
    }, [value]);

    return (
        <Box sx={{
            width: '100%',
            minWidth: 500,
            minHeight: 829,
            pt: '90px',
            display: 'flex',
            justifyContent: 'center',
            // backgroundColor: 'cyan'
        }}>
            <Nav setValue={setValue}></Nav>

            <Masonry columns={{ xs: 2, sm: 3, md: 4, lg: 5, xl: 6 }} spacing={2}>
                {itemData.map((item, index) => (
                    <div
                        key={index}
                    >

                        <ImageDetail
                            item={item}
                            index={index}
                        />
                    </div>
                ))}
            </Masonry>
        </Box>
    );
}

// const itemData = [
//     {
//         img: 'https://images.unsplash.com/photo-1518756131217-31eb79b20e8f',
//         title: 'Fern',
//     },
//     {
//         img: 'https://images.unsplash.com/photo-1627308595229-7830a5c91f9f',
//         title: 'Snacks',
//     },
// ];