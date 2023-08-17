package penterest.spring.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.member.dto.*;
import penterest.spring.domain.member.service.AuthService;
import penterest.spring.domain.member.service.MemberService;
import penterest.spring.global.exception.ValidationException;
import penterest.spring.global.jwt.JwtFilter;
import penterest.spring.global.jwt.TokenProvider;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MemberSignUpDto> signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) {
        try {
            memberService.signUp(memberSignUpDto);
            return ResponseEntity.ok(memberSignUpDto);
        } catch (ValidationException ex) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("validationError", ex.getMessage());
            return ResponseEntity.badRequest().body((MemberSignUpDto) errorMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticateAndGenerateToken(@Valid @RequestBody LoginDto loginDto) {
        try {
            TokenDto tokenDto = memberService.authenticateAndGenerateToken(loginDto);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getToken());
            return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenDto(null, "인증에 실패하였습니다."));
        }
    }


}
