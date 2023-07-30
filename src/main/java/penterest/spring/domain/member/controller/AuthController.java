package penterest.spring.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.member.dto.MemberRequestDto;
import penterest.spring.domain.member.dto.MemberResponseDto;
import penterest.spring.domain.member.dto.MemberSignUpDto;
import penterest.spring.domain.member.service.AuthService;
import penterest.spring.domain.member.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        memberService.signUp(memberSignUpDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        authService.login(memberSignUpDto);
    }
}
