package penterest.spring.domain.member.service;

import penterest.spring.domain.member.dto.*;
import penterest.spring.domain.member.entity.Role;

import java.util.List;

public interface MemberService {

    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;

    void updatePassword(String asIsPassword, String toBePassword, String username) throws Exception;

    void withdraw(String checkPassword, String username) throws Exception;

    MemberInfoDto getInfo(Long id) throws Exception;

    MemberInfoDto getMyInfo() throws Exception;

    List<MemberGifDto> getGifInfoByEmail(String email) throws Exception;

    TokenDto authenticateAndGenerateToken(LoginDto loginDto);

    Role updateRole(String email);
}