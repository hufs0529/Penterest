import { Link } from "react-router-dom";
import { useState } from 'react'
import * as React from 'react';
import { Divider, IconButton, InputBase, Paper, Tooltip } from '@mui/material';
import { Button, Stack, TextField } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SearchIcon from '@mui/icons-material/Search';
// import UploadModal from './UploadModalOriginal';
import UploadModal from './UploadModal';

export default function Nav(props) {

    const [searchTerm, setSearchTerm] = useState("");

    const senseChangeTerm = (event) => {
        setSearchTerm(event.target.value);
      };

    const handleSearchTermChange = (event) => {
        if (event.key === 'Enter') {
          //this.setState({ searchTerm: event.target.value });
          props.setValue(searchTerm);
        }
      };

    const handleSearchTermChangeByClick = (event) => {
        props.setValue(searchTerm);
    }

      

    return (
        <>
            <header
                style={{
                    position: 'fixed',
                    top: '0',
                    width: '100%',
                    height: '80px',
                    zIndex: 999,
                    margin: 'auto',
                    backgroundColor: 'white',
                    display: 'flex',
                    flex: '1 1 auto',
                    justifycontent: 'space-between',
                    alignItems: 'center'
                }}>
                <div className="nav logo"
                    style={{
                        width: '50px',
                        height: '50px',
                        marginLeft: '10px',
                        borderRadius: '15px',
                        overflow: 'hidden',
                        flexGrow: 0,
                        flexShrink: 0,
                        flexbasis: 'auto'
                    }}>
                    <h1
                        style={{
                            marginTop: '4px',
                            transform: 'scale(1.8)',
                            width: '50px',
                            height: '70px',
                            backgroundSize: "cover",
                            backgroundImage: `url(../images/logo.png)`,
                            backgroundPosition: "center center",
                        }} />
                </div>

                <UploadModal />

                <div style={{
                    flexGrow: 1,
                    minWidth: '400px',
                }}>

                    <Stack
                        spacing={0}
                        direction="row"
                        sx={{ mt: "6px" }}>
                        <TextField
                            // InputLabelProps="원하시는 움짤을 검색해보세요"
                            fullWidth
                            id="searchBox"
                            placeholder="원하시는 GIF를 검색해보세요"
                            variant="outlined"
                            onChange={senseChangeTerm}
                            onKeyDown={handleSearchTermChange}
                            size="small"
                            sx={{
                                // height: "10px"
                            }}
                        />
                        <Button
                            variant="text"
                            onClick={handleSearchTermChangeByClick}
                            style={{ ml: 1, fontWeight: "bold" }}
                        >
                            <SearchIcon
                                color="action"
                                sx={{ fontSize: 30 }}
                            />
                        </Button>
                    </Stack>
 
                </div>
                <div style={{
                    flexGrow: 0,
                    flexShrink: 0,
                    flexbasis: 'auto',
                }}>
                    <Tooltip title="로그인">
                        <Link
                            to="/Login"
                            style={{
                                marginLeft: "5px",
                                marginRight: "10px",
                                fontWeight: 'bold',
                            }}
                        >
                            {/* 로그인 */}
                            <AccountCircleIcon
                                color='action'
                                fontSize='large'
                            />
                        </Link>
                    </Tooltip>
                </div>
            </header>


        </>
    )
}