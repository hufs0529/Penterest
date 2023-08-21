package penterest.spring.domain.member.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.member.dto.MemberGifDto;
import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.dto.MemberWithdrawDto;
import penterest.spring.domain.member.dto.UpdatePasswordDto;
import penterest.spring.domain.member.entity.Member;
import penterest.spring.domain.member.entity.Role;
import penterest.spring.domain.member.service.MemberService;
import penterest.spring.global.security.util.SecurityUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public String updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        memberService.updatePassword(updatePasswordDto.checkPassword(), updatePasswordDto.toBePassword(), SecurityUtil.getLoginUserEmail());
        return "비밀번호가 변경되었습니다";
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String withdraw(@Valid @RequestBody MemberWithdrawDto memberWithdrawDto) throws Exception {
        memberService.withdraw(memberWithdrawDto.checkPassword(), SecurityUtil.getLoginUserEmail());
        return "계정이 삭제되었습니다";
    }

    @GetMapping("/search/{id}")
    public ResponseEntity getInfo(@Valid @PathVariable("id") Long id) throws Exception {
        MemberInfoDto memberInfoDto = memberService.getInfo(id);
        return new ResponseEntity(memberInfoDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getMyInfo() throws Exception {

        MemberInfoDto memberInfoDto = memberService.getMyInfo();
        return new ResponseEntity(memberInfoDto, HttpStatus.OK);
    }

    @GetMapping("/search/gif/{email}")
    public List<MemberGifDto> getGifInfoByEmail(@PathVariable String email) throws Exception {
        return memberService.getGifInfoByEmail(email);
    }

    @PutMapping("/role/update/{email}")
    public ResponseEntity<Role> updateRole(@PathVariable String email) {
        Role updatedRole = memberService.updateRole(email);

        if (updatedRole == Role.ADMIN) {
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.ok(updatedRole);
        }
    }
}