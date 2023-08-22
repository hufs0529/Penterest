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
import { useCallback, useState } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";

const JoinUrl = "http://localhost:8080/api/v1/users/join";
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

export default function SignUp() {
  //이름, 이메일, 비밀번호, 비밀번호 확인
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");

  const createDate = new Date();

  //오류메시지 상태저장
  const [nameMessage, setNameMessage] = useState("");
  const [emailMessage, setEmailMessage] = useState("");
  const [phoneNumberMessage, setPhoneNumberMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [passwordConfirmMessage, setPasswordConfirmMessage] = useState("");

  // 유효성 검사
  const [isName, setIsName] = useState(false);
  const [isEmail, setIsEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [isPasswordConfirm, setIsPasswordConfirm] = useState(false);
  const [isPhoneNumber, setIsPhoneNumber] = useState(false);

  // 버튼 통제 함수
  const [signupBtnDisabled, SetSignupBtnDisabled] = useState(true);

  // 규약동의버튼 핸들러
  const [checked, setChecked] = useState(false);
  const handleCheckChange = (event) => {
    setChecked(event.target.checked);
    if (checked === true) {
      SetSignupBtnDisabled(true);
    } else {
      SetSignupBtnDisabled(false);
    }
  };

  const requestToBackend = {
    emailAddress: email,
    password: password,
    phoneNumber: phoneNumber,
    userName: name,
    createDate: createDate,
  };

  const navigate = useNavigate();

  const onSubmitHandler = useCallback(
    async (e) => {
      e.preventDefault();

      try {
        await axios.post(JoinUrl, requestToBackend).then((response) => {
          console.log(response.data);
          console.log(requestToBackend);

          if (response.status === 200) {
            
            alert("회원가입이 완료되었습니다!");
            navigate("/Login");
          }
        });
      } catch (err) {
        window.alert("error");
        console.error(err);
      }
    },
    [requestToBackend]
  );

  // 이름
  const onChangeName = useCallback((e) => {
    setName(e.target.value);
    if (e.target.value.length < 2 || e.target.value.length > 5) {
      setNameMessage("2글자 이상 5글자 미만으로 입력해주세요.");
      setIsName(false);
    } else {
      setNameMessage("Good!");
      setIsName(true);
    }
  }, []);

  // 핸드폰 번호
  const onChangePhoneNumber = useCallback((e) => {
    const phoneRegex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
    const phoneNumberCurrent = e.target.value;
    setPhoneNumber(phoneNumberCurrent);

    if (!phoneRegex.test(phoneNumberCurrent)) {
      setPhoneNumberMessage("010AAAABBBB 형식으로 입력해주세요!");
      setIsPhoneNumber(false);
    } else {
      setPhoneNumberMessage("Good!");
      setIsPhoneNumber(true);
    }
  }, []);

  // 이메일
  const onChangeEmail = useCallback((e) => {
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    const emailCurrent = e.target.value;
    setEmail(emailCurrent);

    if (!emailRegex.test(emailCurrent)) {
      setEmailMessage("이메일을 바르게 입력해주세요");
      setIsEmail(false);
    } else {
      setEmailMessage("Good!");
      setIsEmail(true);
    }
  }, []);

  // 비밀번호
  const onChangePassword = useCallback((e) => {
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    const passwordCurrent = e.target.value;
    setPassword(passwordCurrent);

    if (!passwordRegex.test(passwordCurrent)) {
      setPasswordMessage(
        "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
      );
      setIsPassword(false);
    } else {
      setPasswordMessage("Good!");
      setIsPassword(true);
    }
  }, []);

  // 비밀번호 확인
  const onChangePasswordConfirm = useCallback(
    (e) => {
      const passwordConfirmCurrent = e.target.value;
      setPasswordConfirm(passwordConfirmCurrent);

      if (password === passwordConfirmCurrent) {
        setPasswordConfirmMessage("good");
        setIsPasswordConfirm(true);
      } else {
        setPasswordConfirmMessage("비밀번호를 다시 한번 입력해주세요!");
        setIsPasswordConfirm(false);
      }
    },
    [password]
  );

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
            padding: "0",
          }}>
          <CssBaseline />
          <Box
            sx={{
              marginTop: "3px",
              marginLeft: 1,
            }}>
            <Link to="/">
              <HomeIcon fontSize="large">메인 페이지</HomeIcon>
            </Link>
          </Box>
          <Box
            sx={{
              // marginTop: 0,
              marginLeft: { xs: 1 },
              marginRight: { xs: 1 },
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}>
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              회원가입
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={onSubmitHandler}
              sx={{ mt: 3 }}>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    autoComplete="name"
                    required
                    fullWidth
                    id="Name"
                    label="이름"
                    autoFocus
                    value={name}
                    onChange={onChangeName}
                    type="text"
                    helperText={nameMessage}
                    color={isName ? "primary" : "error"}
                  />
                </Grid>

                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="email"
                    label="이메일 주소"
                    name="email"
                    autoComplete="email"
                    value={email}
                    onChange={onChangeEmail}
                    helperText={emailMessage}
                    color={isEmail ? "primary" : "error"}
                  />
                </Grid>

                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="phoneNumber"
                    label="핸드폰 번호"
                    id="phoneNumber"
                    autoComplete="new-phoneNumber"
                    value={phoneNumber}
                    onChange={onChangePhoneNumber}
                    helperText={phoneNumberMessage}
                    color={isPhoneNumber ? "primary" : "error"}
                  />
                </Grid>

                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="password"
                    type="password"
                    id="password"
                    label="비밀번호"
                    autoComplete="current-password"
                    value={password}
                    onChange={onChangePassword}
                    text="비밀번호 (숫자+영문자+특수문자 조합으로 8자리 이상)"
                    helperText={passwordMessage}
                    color={isPassword ? "primary" : "error"}
                  />
                </Grid>

                {/* 비밀번호 컨펌 폼 추가 by jakeham  */}
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    value={passwordConfirm}
                    onChange={onChangePasswordConfirm}
                    name="confirmPassword"
                    label="비밀번호 확인"
                    type="password"
                    id="confirmPassword"
                    autoComplete="current-password"
                    helperText={passwordConfirmMessage}
                    color={isPasswordConfirm ? "primary" : "error"}
                  />
                </Grid>

                <Grid item xs={12}>
                  <FormControlLabel
                    control={
                      <Checkbox
                        value="agreement"
                        color="primary"
                        checked={checked}
                        onChange={handleCheckChange}
                      />
                    }
                    label={
                      "본 사이트 이용규약과 개인정보 취급정책에 동의합니다."
                    }
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                disabled={
                  !(
                    isName &&
                    isEmail &&
                    isPassword &&
                    isPasswordConfirm &&
                    isPhoneNumber &&
                    checked
                  )
                }>
                회원가입하기!
              </Button>
              <Grid container justifyContent="flex-end">
                <Grid item>
                  <Link
                    to="/Login"
                    style={{
                      fontFamily: "Roboto",
                      fontSize: "14px",
                      color: "blue",
                    }}>
                    이미 계정이 있으신가요? 로그인
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
