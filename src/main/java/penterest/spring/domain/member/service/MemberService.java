package penterest.spring.domain.member.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import penterest.spring.domain.member.dto.*;

import java.util.List;

public interface MemberService {

    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;

    void updatePassword(String asIsPassword, String toBePassword, String username) throws Exception;

    void withdraw(String checkPassword, String username) throws Exception;

    MemberInfoDto getInfo(Long id) throws Exception;

    MemberInfoDto getMyInfo() throws Exception;

    List<MemberGifDto> getGifInfoByEmail(String email) throws Exception;

    TokenDto authenticateAndGenerateToken(LoginDto loginDto);
}