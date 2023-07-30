package penterest.spring.domain.member.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.dto.MemberWithdrawDto;
import penterest.spring.domain.member.dto.UpdatePasswordDto;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.service.MemberService;
import penterest.spring.global.security.util.SecurityUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        memberService.updatePassword(updatePasswordDto.checkPassword(), updatePasswordDto.toBePassword(), SecurityUtil.getLoingUserEmail());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@Valid @RequestBody MemberWithdrawDto memberWithdrawDto) throws Exception {
        memberService.withdraw(memberWithdrawDto.checkPassword(), SecurityUtil.getLoingUserEmail());
    }

    @GetMapping("/{id}")
    public ResponseEntity getInfo(@Valid @PathVariable("id") Long id) throws Exception {
        MemberInfoDto memberInfoDto = memberService.getInfo(id);
        return new ResponseEntity(memberInfoDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getMyInfo() throws Exception {

        MemberInfoDto memberInfoDto = memberService.getMyInfo();
        return new ResponseEntity(memberInfoDto, HttpStatus.OK);
    }
}
