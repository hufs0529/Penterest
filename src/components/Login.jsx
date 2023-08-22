import "@fontsource/roboto/300.css";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Checkbox from "@mui/material/Checkbox";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline";
import FormControlLabel from "@mui/material/FormControlLabel";
import Grid from "@mui/material/Grid";
import MUILink from "@mui/material/Link";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import axios from "axios";
import * as React from "react";
import { Link, useNavigate } from "react-router-dom";

const loginUrl = "http://localhost:8080/api/v1/users/login";
function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}>
      {"Copyright © "}
      <MUILink color="inherit" href="#">
        Kim & Ham
      </MUILink>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

export default function Login() {
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = data.get("email");
    const password = data.get("password");

    const requestToBackend = {
      emailAddress: email,
      password: password,
    };

    axios
      .post(loginUrl, requestToBackend)
      .then((response) => {
        const { accessToken } = response.data;
        // // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
        axios.defaults.headers.common[
          "Authorization"
        ] = `Bearer ${accessToken}`;

        alert("성공");
        console.log(requestToBackend);

        // accessToken 출력
        console.log(response.data);
        navigate("/");
      })
      .catch((error) => {
        console.log(error.response);
      });
  };

  return (
    <Box
      sx={{
        width: "100%",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        backgroundColor: "white",
      }}>
      <Box
        sx={{
          width: { sm: "0%", md: "50%", lg: "33.33%" },
          height: "100vh",
          backgroundImage: `url(../images/gallery/1.jpeg)`,
          backgroundRepeat: "no-repeat",
          backgroundPosition: "left",
          backgroundSize: "cover",
          overflow: "hidden",
        }}></Box>
      <ThemeProvider theme={theme}>
        <Container
          component="main"
          maxWidth="xs"
          sx={{
            backgroundColor: { sm: "white" },
          }}>
          <CssBaseline />
          <Box
            sx={{
              marginTop: "15px",
              marginLeft: 1,
            }}>
            <Link to="/"> 메인페이지 </Link>
          </Box>
          <Box
            sx={{
              marginTop: 6,
              marginLeft: { xs: 1 },
              marginRight: { xs: 1 },
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}>
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h2" variant="h6">
              Welcome!
            </Typography>
            <Typography component="h1" variant="h5">
              로그인
            </Typography>
            <Box
              component="form"
              onSubmit={handleSubmit}
              noValidate
              sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="이메일 주소"
                name="email"
                autoComplete="email"
                autoFocus
              />

              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="비밀번호"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="자동 로그인"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}>
                로그인하기!
              </Button>

              <Grid container>
                <Grid item xs>
                  <MUILink href="#" variant="body2">
                    비밀번호 찾기
                  </MUILink>
                </Grid>
                <Grid item>
                  <Link
                    to="/Signup"
                    style={{
                      fontFamily: "Roboto",
                      fontSize: "14px",
                      color: "blue",
                    }}>
                    {"계정이 없으신가요? 회원가입"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
          <Copyright sx={{ mt: 5 }} />
        </Container>
      </ThemeProvider>
      <Box
        sx={{
          width: { sm: "0", md: "0", lg: "33.33%" },
          height: "100vh",
          backgroundImage: `url(../images/gallery/9.jpeg)`,
          backgroundRepeat: "no-repeat",
          backgroundPosition: "right",
          backgroundSize: "cover",
          overflow: "hidden",
        }}></Box>
    </Box>
  );
}
