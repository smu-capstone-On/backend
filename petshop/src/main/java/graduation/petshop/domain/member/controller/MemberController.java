package graduation.petshop.domain.member.controller;

import graduation.petshop.domain.member.dto.request.JoinDto;
import graduation.petshop.domain.member.dto.request.RequestFindIdDto;
import graduation.petshop.domain.member.dto.response.ResponseFindIdDto;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * service에 회원가입 안 넣고 Controller에 넣었다.
     * 분리가 필요하다 생각함
     */
    @PostMapping("member/add")
    public ResponseEntity<Object> joinMember(@RequestBody @Valid JoinDto joinDto){
        log.info("회원가입 완료");
        Member member = joinDto.toEntity(
                joinDto.getLoginId(),
                joinDto.getPassWard(),
                joinDto.getEmail()
        );
        memberService.join(member);
        return ResponseEntity.ok("ok");
    }

    /**
     * 아이디찾기
     *
     */
    @GetMapping("member/find-id")
    public ResponseFindIdDto findByEmail(@RequestBody @Valid RequestFindIdDto requestFindIdDto){
        Member member = memberService.findIdByEmail(requestFindIdDto.getEmail());
        return new ResponseFindIdDto(member);
    }
}
