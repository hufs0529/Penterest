package penterest.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import penterest.spring.domain.member.controller.AuthController;
import penterest.spring.domain.member.dto.LoginDto;
import penterest.spring.domain.member.dto.TokenDto;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.repository.MemberRepository;
import penterest.spring.domain.member.service.MemberServiceImpl;
import penterest.spring.global.jwt.TokenProvider;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class test {

    @Mock
    private AuthController authController;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMyInfo() {
        // Test getMyInfo() method here (previous test code)

        // ...
    }

    @Test
    public void testAuthorize() {
        // Arrange
        String userEmail = "user@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword"; // replace this with the actual encoded password
        LoginDto loginDto = new LoginDto(userEmail, password);
        Set<Authority> authorities = new HashSet<>(); // populate the authorities if needed
        Member mockMember = new Member(userEmail, encodedPassword, authorities);
        when(memberRepository.findByEmail(userEmail)).thenReturn(mockMember);

        String jwt = "testJWT"; // Replace this with the expected JWT token
        when(tokenProvider.generateToken(any())).thenReturn(jwt);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEmail, password);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        ResponseEntity<TokenDto> response = authController.authenticateAndGenerateToken(loginDto);

        // Assert
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(jwt, response.getBody().getToken());

        // Verify that the authenticationManager.authenticate() was called with the correct argument
        verify(authenticationManager, times(1)).authenticate(any());

        // Verify that the tokenProvider.generateToken() was called with the correct argument
        verify(tokenProvider, times(1)).generateToken(authentication);
    }
}
