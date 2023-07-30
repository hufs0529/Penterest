package penterest.spring.domain.member.service;

import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.dto.MemberSignUpDto;

public interface MemberService {

    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;


    void updatePassword(String asIsPassword, String toBePassword, String username) throws Exception;

    void withdraw(String checkPassword, String username) throws Exception;

    MemberInfoDto getInfo(Long id) throws Exception;

    MemberInfoDto getMyInfo() throws Exception;
}
