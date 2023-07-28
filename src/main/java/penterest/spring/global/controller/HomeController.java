//package penterest.spring.global.controller;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import penterest.spring.domain.member.dto.MemberRequestDto;
//import penterest.spring.domain.member.dto.MemberResponseDto;
//import penterest.spring.domain.member.service.CustomUserDetailService;
//import penterest.spring.global.security.util.JwtUtil;
//
//@RestController
//@RequiredArgsConstructor
//public class HomeController {
//
//    private final CustomUserDetailService userDetailService;
//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//
//
//    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }
//
//    @PostMapping("/auth_test")
//    public ResponseEntity<MemberResponseDto> authenticateTest(@RequestBody MemberRequestDto memberRequestDto) {
//        try {
//            // username, password 인증 시도
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(memberRequestDto.getEmail(), memberRequestDto.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("로그인 실패");
//        }
//        // 인증 성공 후 인증된 user의 정보를 갖고옴
//        UserDetails userDetails = userDetailService.loadUserByUsername(memberRequestDto.getEmail());
//        // subject, claim 모두 UserDetails를 사용하므로 객체를 그대로 전달
//        String token = jwtUtil.generateToken(userDetails);
//
//        // 생성된 토큰을 응답 (Test)
//        return ResponseEntity.ok(new MemberResponseDto(token));
//    }
//
//}
